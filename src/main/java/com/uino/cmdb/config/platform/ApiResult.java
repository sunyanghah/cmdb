package com.uino.cmdb.config.platform;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 响应格式封装
 * @author sunYang
 * @date 2021/9/16 16:32
 */
@Data
@ToString
@NoArgsConstructor
public class ApiResult<T> implements Serializable {

    public static final int successCode = 200;
    public static final String successMsg = "SUCCESS";
    public static final int failCode = 500;
    public static final String failMsg = "FAIL";

    private static final long serialVersionUID = 1L;
    /**
     * 服务端接口是否正常
     */
    private Boolean status;
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;


    public ApiResult(T data) {
        this(Boolean.TRUE, successCode, successMsg, data);
    }

    public ApiResult(Boolean status, int code, String msg) {
        this(status, code, msg, null);
    }

    public ApiResult(Boolean status, int code, String msg, T data) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static final <T> ApiResult<T> success() {
        return new ApiResult(Boolean.TRUE, successCode, successMsg);
    }

    public static final <T> ApiResult<T> success(T data) {
        return new ApiResult(data);
    }

    public static final <T> ApiResult<T> fail() {
        return new ApiResult(Boolean.FALSE, failCode, failMsg);
    }

    public static final <T> ApiResult<T> fail(int code, String msg) {
        return new ApiResult(Boolean.FALSE, code, msg);
    }

    public static final <T> ApiResult<T> fail(String msg) {
        return new ApiResult(Boolean.FALSE, failCode, msg);
    }

    public static final <T> ApiResult<T> fail(int code ,String msg, T data){
        return new ApiResult<>(Boolean.FALSE,code,msg,data);
    }

}
