package com.fuyunwang.chuoyue.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuyunwang.chuoyue.common.base.ResponseCode;
import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.common.exception.BadMobileCodeException;
import com.fuyunwang.chuoyue.common.exception.MobileNotFoundException;
import com.fuyunwang.chuoyue.common.exception.VerifyCodeErrorException;
import com.fuyunwang.chuoyue.common.utils.GlobalUtil;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * @author: FuyunWang
 * @time: 2020/7/25 15:30
 */
@Component
public class ChuoyueAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ResponseResult responseResult=null;
        if (exception instanceof LockedException) {
            responseResult=ResponseResult.createByError(ResponseCode.LOGIN_FAILURE.getCode(),ResponseCode.LOGIN_FAILURE.getDesc(), GlobalUtil.data("账户被锁定，请联系管理员!"));
        } else if (exception instanceof CredentialsExpiredException) {
            responseResult=ResponseResult.createByError(ResponseCode.LOGIN_FAILURE.getCode(),ResponseCode.LOGIN_FAILURE.getDesc(),GlobalUtil.data("密码过期，请联系管理员!"));
        } else if (exception instanceof AccountExpiredException) {
            responseResult=ResponseResult.createByError(ResponseCode.LOGIN_FAILURE.getCode(),ResponseCode.LOGIN_FAILURE.getDesc(),GlobalUtil.data("账户过期，请联系管理员!"));
        } else if (exception instanceof DisabledException) {
            responseResult=ResponseResult.createByError(ResponseCode.LOGIN_FAILURE.getCode(),ResponseCode.LOGIN_FAILURE.getDesc(),GlobalUtil.data("账户被禁用，请联系管理员!"));
        } else if (exception instanceof BadCredentialsException) {
            responseResult=ResponseResult.createByError(ResponseCode.LOGIN_FAILURE.getCode(),ResponseCode.LOGIN_FAILURE.getDesc(),GlobalUtil.data("用户名或者密码输入错误，请重新输入!"));
        }else if (exception instanceof BadMobileCodeException){
            responseResult=ResponseResult.createByError(ResponseCode.LOGIN_FAILURE.getCode(),ResponseCode.LOGIN_FAILURE.getDesc(),GlobalUtil.data("手机号验证码错误,登陆失败"));
        }else if (exception instanceof MobileNotFoundException){
            responseResult=ResponseResult.createByError(ResponseCode.LOGIN_FAILURE.getCode(),ResponseCode.LOGIN_FAILURE.getDesc(),GlobalUtil.data("当前手机号未注册,登陆失败"));

        }else if (exception instanceof VerifyCodeErrorException){
            responseResult=ResponseResult.createByError(ResponseCode.LOGIN_FAILURE.getCode(),ResponseCode.LOGIN_FAILURE.getDesc(),GlobalUtil.data("验证码输入有误"));

        }
        out.write(new ObjectMapper().writeValueAsString(responseResult));
        out.flush();
        out.close();
    }
}
