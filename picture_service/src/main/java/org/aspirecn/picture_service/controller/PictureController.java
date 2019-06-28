package org.aspirecn.picture_service.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.aspirecn.picture_service.po.PictureResult;
import org.aspirecn.picture_service.po.ResultObject;
import org.aspirecn.picture_service.po.ValidPicture;
import org.aspirecn.picture_service.service.EditorPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 图片处理类
 *
 * @author  cg
 *
 */
@RestController
public class PictureController {


    @Autowired
    EditorPicture editorPicture;



    @ApiOperation(value = "得到验证图和滑块阴影图和验证图所在滑块阴影图y坐标key值(x轴)", notes = "")
    @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "query")
    @GetMapping("/getPicture")
    public ResultObject getPicture(@RequestParam(name = "userName", required = true) String userName){
        ResultObject ro= new ResultObject();
        try {
            if (userName == null) {
                throw new RuntimeException("用户名为空");
            }
            PictureResult pr= editorPicture.editorPicture(userName);
            if (pr.getYyPng() == null && pr.getCutPng() == null) {
                throw new RuntimeException("失败");
            }
            ro.setCode(1000);
            ro.setMessage("成功");
            ro.setResult(pr);

        }catch (Exception e){
            ro.setCode(-1);
            ro.setMessage(e.getMessage());
            e.getMessage();
            e.printStackTrace();
        }
        return ro;
    }
    @ApiOperation(value = "校验滑块是否在图片指定位置", notes = "")
    @PostMapping("/validPicture")
    public ResultObject validPicture(@RequestBody ValidPicture validPicture){
        ResultObject ro= new ResultObject();
        boolean valid;
        try {
            if(validPicture.getKey()!=null && validPicture.getDistance()!=0)
            {
                valid= editorPicture.validPicture(validPicture.getKey(),validPicture.getDistance());

                if(!valid){
                    throw new RuntimeException("失败，超过误差");
                }else {
                    ro.setCode(1000);
                    ro.setMessage("成功");
                }
            }else {
                throw new RuntimeException("参数为空");
            }

        }catch (Exception e){
            ro.setCode(-1);
            ro.setMessage(e.getMessage());
            e.getMessage();
            e.printStackTrace();
        }
        return ro;
    }



}
