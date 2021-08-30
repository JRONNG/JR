package com.jr.gitdemo.common;



import java.io.Serializable;

/**
 * Created by lenovo on 2018/5/25.
 */
//@ApiModel
public class ResponseModel<T> implements Serializable{
//    @ApiModelProperty(value = "状态")
    private boolean status;
//    @ApiModelProperty(value = "状态码",example = "00001")
    private String code;
//    @ApiModelProperty(value = "错误信息",example = "成功")
    private String message;
//    @ApiModelProperty(value = "数据")
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
