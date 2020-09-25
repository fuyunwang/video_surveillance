package com.fuyunwang.chuoyue.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuyunwang.chuoyue.common.base.ResponseCode;
import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.common.utils.DatetimeUtil;
import com.fuyunwang.chuoyue.common.utils.JwtTokenUtil;
import com.fuyunwang.chuoyue.system.entity.TbAgent;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @description:
 * @author: FuyunWang
 * @time: 2020/7/25 15:28
 */
@Component
public class ChuoyueAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        TbAgent principal = (TbAgent) authentication.getPrincipal();
        principal.setAgentPassword(null);
        String token = jwtTokenUtil.generateToken(principal.getUsername());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,String> data= Maps.newConcurrentMap();
        data.put("username",principal.getAgentName());
        data.put("token",token);
        data.put("message", principal.getAgentName()+"于"+ DatetimeUtil.formatFullTime(LocalDateTime.now()) +"登陆");
        String s = new ObjectMapper().writeValueAsString(
                ResponseResult.createBySuccess(ResponseCode.LOGIN_SUCCESS.getCode(),ResponseCode.LOGIN_SUCCESS.getDesc(),
                        data));
        out.write(s);
        out.flush();
        out.close();
    }

}
