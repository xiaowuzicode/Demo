package org.aspirecn.picture_service.service;


import org.aspirecn.picture_service.po.PictureResult;
import org.aspirecn.picture_service.po.ResultObject;

import java.io.IOException;


/**
 * 编辑图片接口
 * @auther cg
 */
public interface EditorPicture {

    /**
     * 返回 小图相比大图，长宽的距离 阴影图base64编码 小图base64编码
     *
     * @return
     */
    public PictureResult editorPicture(String userName) throws IOException;


    public Boolean validPicture(String key,Integer distance);


}
