package com.uino.cmdb.config.exception;


import com.uino.cmdb.config.enums.ExceptionCodeE;
import com.uino.cmdb.config.platform.ApiResult;
import com.uino.cmdb.config.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
@Slf4j
class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult defaultExceptionHandler(HttpServletRequest req, Exception e) {
    	String exceptionInterface = req.getRequestURI();
    	log.error("{\"applicationService\":\"xxv-server\",\"exceptionType\":\"Exception\",\"exceptionTime\":\""+
				DateUtils.formatPatternDatetime(new Date()) +"\","
				+ "\"exceptionInterface\":\""+exceptionInterface+"\",\"exceptionInfo\":\""+e.getMessage()+"\"}");
		e.printStackTrace();
    	return ApiResult.fail(ExceptionCodeE.SERVER_ERROR.getCode(),ExceptionCodeE.SERVER_ERROR.getMsg(),e.getMessage());
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ApiResult defaultBusinessExceptionHandler(HttpServletRequest req, BusinessException e) throws Exception {
		String exceptionInterface = req.getRequestURI();
		log.error("{\"applicationService\":\"xxv-server\",\"exceptionType\":\"BusinessException\",\"exceptionTime\":\""+
				DateUtils.formatPatternDatetime(new Date()) +"\","
				+ "\"exceptionInterface\":\""+exceptionInterface+"\",\"exceptionInfo\":\""+e.getMessage()+"\"}");
		return ApiResult.fail(e.getStatus(),e.getMessage(),e.getMessage());
    }

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseBody
	public ApiResult defaultArgumentExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) throws Exception {
		String exceptionInterface = req.getRequestURI();
		log.error("{\"applicationService\":\"xxv-server\",\"exceptionType\":\"MethodArgumentNotValidException\",\"exceptionTime\":\""+
				DateUtils.formatPatternDatetime(new Date()) +"\","
				+ "\"exceptionInterface\":\""+exceptionInterface+"\",\"exceptionInfo\":\""+e.getMessage()+"\"}");
		return ApiResult.fail(ExceptionCodeE.PARAM_VALID_ERROR.getCode(),ExceptionCodeE.PARAM_VALID_ERROR.getMsg(),
				e.getBindingResult().getFieldError().getDefaultMessage());
	}
}
