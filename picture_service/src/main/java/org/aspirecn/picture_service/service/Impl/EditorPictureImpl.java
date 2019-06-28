package org.aspirecn.picture_service.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspirecn.picture_service.po.PictureResult;
import org.aspirecn.picture_service.service.EditorPicture;
import org.aspirecn.picture_service.util.ChangePictureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @auther cg
 */
@Slf4j
@Service
public class EditorPictureImpl implements EditorPicture {


    static final ObjectMapper mapper = new ObjectMapper();

    @Value("${picture.sourcepath}")
    private String sourcepath;



    @Value("${picture.count}")
    private Integer count;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    //小图长
    static int targetLength=55;
    //小图宽
    static int targetWidth=45;
    //半径
    static int circleR=8;
    //距离点
    static int r1=5;
    //验证精度距离
    static int validdistance=5;
    /**
     *  获取滑动长度校验
     * @param key
     * @param distance
     * @return
     */
    @Override
    public Boolean validPicture(String key,Integer distance) throws RuntimeException{

        if(!stringRedisTemplate.hasKey(key)){
            log.info("不存在对应的key");
            throw new RuntimeException("key不存在或已被使用");
        }
        String x=stringRedisTemplate.opsForValue().get(key);
        System.out.println("distance="+distance+",key="+key+",x="+x);
        Integer lengthX=Integer.valueOf(x);
        if(distance<=lengthX+validdistance && distance >= lengthX-validdistance){
            log.info("准确");
            stringRedisTemplate.delete(String.valueOf(key));
            return true;
        }
        log.info("超过误差");
        return false;
    }

    @Override
    public PictureResult editorPicture(String userName) throws IOException,RuntimeException {
            PictureResult pr= new PictureResult();

            //原图
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(ChangePictureUtil.getSourcepath(sourcepath,count)));
            BufferedImage target= new BufferedImage(targetLength, targetWidth, BufferedImage.TYPE_4BYTE_ABGR);

            //指定最大最小值的随机数
            Random r = new Random();
            int x=r.ints(80, 240).findFirst().getAsInt();

            int y=r.ints(20, 70).findFirst().getAsInt();

            log.info("x轴等于{}，y轴等于{}",x,y);

            cutByTemplate(bufferedImage,target,getBlockData(),x,y);

            //随机key 三个随机数拼接 组成8位随机数
         //   String key=String.valueOf((int)((Math.random()*9+1)*100000)+x)+y;
            //多平台使用工具类，改用uuid
            String key=UUID.randomUUID().toString().replace("-", "");
            System.out.println("随机key="+key);
            log.info("随机key={}",key);
            stringRedisTemplate.opsForValue().set(key,String.valueOf(x),60*10,TimeUnit.SECONDS);

            pr.setKey(key);
            pr.setCjy(y);
            pr.setYyPng(getImageBASE64(bufferedImage));
            pr.setCutPng(getImageBASE64(target));
            return pr;
    }


    /**
     * @Title: getBlockData
     * @Description: 生成小图轮廓
     * @return int[][]
     * @throws
     */
    private static int[][] getBlockData() {

        int[][] data = new int[targetLength][targetWidth];
        double x2 = targetLength-circleR;

        //随机生成圆的位置
        double h1 = circleR + Math.random() * (targetWidth-3*circleR-r1);
        double po = circleR*circleR;

        double xbegin = targetLength-circleR-r1;
        double ybegin = targetWidth-circleR-r1;

        for (int i = 0; i < targetLength; i++) {
            for (int j = 0; j < targetWidth; j++) {
                double d3 = Math.pow(i - x2,2) + Math.pow(j - h1,2);
                double d2 = Math.pow(j-2,2) + Math.pow(i - h1,2);
                if ((j <= ybegin && d2 <= po)||(i >= xbegin && d3 >= po)) {
                    data[i][j] = 0;
                }  else {
                    data[i][j] = 1;
                }

            }
        }
        return data;

    }


    /**
     *
     * @Title: cutByTemplate
     * @Description: 生成小图片、给大图片添加阴影
     * @param oriImage
     * @param targetImage
     * @param templateImage
     * @param x
     * @param y void
     * @throws
     */
    private void cutByTemplate(BufferedImage oriImage,BufferedImage targetImage, int[][] templateImage, int x,int y) throws IOException{



        for (int i = 0; i < targetLength; i++) {
            for (int j = 0; j < targetWidth; j++) {
                int rgb = templateImage[i][j];
                // 原图中对应位置变色处理
                int rgb_ori = oriImage.getRGB(x + i, y + j);

                if (rgb == 1) {
                    //抠图上复制对应颜色值
                    targetImage.setRGB(i, j, rgb_ori);
                    // 一 原图修改添加颜色，灰度方法
                    oriImage.setRGB(x + i, y + j, 0xff808A87 & rgb_ori);
                }else{
                    //这里把背景设为透明
                    targetImage.setRGB(i, j, 0x00ffffff &rgb_ori);
                }
            }
        }



        //保存阴影图
       // getSaveImage(oriImage);
        //保存扣图
       // getSaveImage(targetImage);




    }

    /**
     * * @Description: 图片转BASE64
     * @param image
     * @return
     * @throws IOException String
     * @throws
     */
    public static String getImageBASE64(BufferedImage image) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image,"png",out);
        //转成byte数组
        byte[] b = out.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        //生成base64编码
        return encoder.encode(b);
      //  return encoder.encodeBuffer(b).trim();
    }


    /**
     * * @Description: 临时方法，切图保存 图片转BASE64
     * @param image
     * @return
     * @throws IOException String
     * @throws
     */
    public void getSaveImage(BufferedImage image) throws IOException {
        File outfile = new File(sourcepath+(int)((Math.random()*9+1)*100000)+"cut.png");
        ImageIO.write(image, "png", outfile);
    };




}
