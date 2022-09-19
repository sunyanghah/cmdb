package com.uino.cmdb.config.exception;

import com.uino.cmdb.config.enums.ExceptionCodeE;
import lombok.Data;

/**
 * Created by dell on 2018/9/6.
 * @author dell
 */
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 445445701327085003L;

    private Integer status;

    public BusinessException(){
        this(ExceptionCodeE.SERVER_ERROR);
    }

    public BusinessException(String message){
        this(ExceptionCodeE.SERVER_ERROR.getCode(), message);
    }

    public BusinessException(ExceptionCodeE exceptionCodeE){
        super(exceptionCodeE.getMsg());
        status = exceptionCodeE.getCode();
    }

    public BusinessException(Integer code, String message){
        super(message);
        status = code;
    }
}
