package org.aspirecn.picture_service.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 图片校验
 *
 * @auther cg
 */
@Data
@ToString(callSuper = true, includeFieldNames = true)
public class ValidPicture {

    /**
     * 存x轴位置的key
     */
    @JsonProperty(value = "key")
    private String key;
    /**
     * 滑动距离
     */
    @JsonProperty(value = "distance")
    private int distance;






}
