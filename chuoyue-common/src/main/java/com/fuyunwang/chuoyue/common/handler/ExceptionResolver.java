package com.fuyunwang.chuoyue.common.handler;

import com.fuyunwang.chuoyue.common.base.ResponseCode;
import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.common.exception.InternalException;
import com.fuyunwang.chuoyue.common.exception.MobileNotFoundException;
import com.fuyunwang.chuoyue.common.exception.ParamException;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice(basePackages = {"com.fuyunwang.chuoyue"})
public class ExceptionResolver {

 /*   @ExceptionHandler(InternalException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult<Map<String,String>> errorHandler(InternalException e){
        Map data= Maps.newHashMap();
        data.put("error",e.getErrorMesssage());
        return ResponseResult.createByError(ResponseCode.ERROR.getCode(),
                ResponseCode.ERROR.getDesc(),data);
    }*/

    @ExceptionHandler(ParamException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseResult<Map<String,String>> paramExceptionHandler(ParamException e){
        Map<String,String> data= Maps.newHashMap();
        data.put("error",e.getErrorMesssage());
        return ResponseResult.createByError(ResponseCode.ILLEGAL_ARGUMENTS.getCode(),e.getErrorMesssage(),data);
    }

    //单个请求参数的校验
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult<Map<String,String>> handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        Map data= Maps.newHashMap();
        data.put("error",message);
        return ResponseResult.createByError(ResponseCode.ILLEGAL_ARGUMENTS.getCode(),
                ResponseCode.ILLEGAL_ARGUMENTS.getDesc(),data);
    }

    //对象请求参数的校验
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult<Map<String,String>> handleBindException(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        Map data= Maps.newHashMap();
        data.put("error",message);
        return ResponseResult.createByError(ResponseCode.ILLEGAL_ARGUMENTS.getCode(),
                ResponseCode.ILLEGAL_ARGUMENTS.getDesc(),data);
    }

    //对象请求参数的校验
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult<Map<String,String>> handleMethodArgumentException(MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        Map data= Maps.newHashMap();
        data.put("error",message);
        return ResponseResult.createByError(ResponseCode.ILLEGAL_ARGUMENTS.getCode(),
                ResponseCode.ILLEGAL_ARGUMENTS.getDesc(),data);
    }
}
