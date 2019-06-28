package org.aspirecn.picture_service.util;


import java.util.Random;

/**
 * Created by cg
 */
public class ChangePictureUtil {

    static int targetLength=55;//小图长
    static int targetWidth=45;//小图宽
    static int circleR=8;//半径
    static int r1=5;//距离点

    /**
     *  颜色分量转换为RGB值
     * @param alpha
     * @param red
     * @param green
     * @param blue
     * @return
     */
    public static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }

    //比较三个数的大小
    public static int getBigger(int x,int y,int z){
        if(x>=y&&x>=z){
            return x;
        }else if(y>=x&&y>=z){
            return y;
        }else if(z>=x&&z>=y){
            return z;
        }else{
            return 0;
        }
    }


    /**
     * 多张自定义滑块随机选中选一张验证
     * @param sourcepath 图片路径，jar包部署，绝对路径
     * @param count 图片数量
     * @return
     */
    public static String getSourcepath(String sourcepath,Integer count){

        String name="";
        Random rand = new Random();
        //0 到 count   图片数量
        int num=rand.nextInt(count)+1;
        name = num+".png";
        System.out.println("验证码图片为"+name);
        return sourcepath+name;
    }

}