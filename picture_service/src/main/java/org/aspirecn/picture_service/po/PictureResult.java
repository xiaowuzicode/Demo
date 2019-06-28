package org.aspirecn.picture_service.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 图片返回实体类
 *
 * @auther cg
 */
@Data
@ToString(callSuper = true, includeFieldNames = true)
public class PictureResult {

    /**
     * 随机生产key
     */
    @JsonProperty(value = "key")
    String key;
    /**
     * 裁剪开始Y坐标坐标
     */
    @JsonProperty(value = "cjy")
    int cjy;
    /**
     * 阴影图片base
     */
    @JsonProperty(value = "yyPng")
    String yyPng;
    /**
     * 裁剪图片base
     */
    @JsonProperty(value = "cutPng")
    String cutPng;

}
