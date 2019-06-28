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
public class ResultObject {

    /**
     * 返回码
     */
    @JsonProperty(value = "code")
    private int code;
    /**
     * 消息
     */
    @JsonProperty(value = "message")
    private String message;
    /**
     * 对象
     */
    @JsonProperty(value = "result")
    private Object result;





}
