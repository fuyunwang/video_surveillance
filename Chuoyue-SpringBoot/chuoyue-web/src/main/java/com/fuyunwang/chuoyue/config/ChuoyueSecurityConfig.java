package com.fuyunwang.chuoyue.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuyunwang.chuoyue.common.base.GlobalConstant;
import com.fuyunwang.chuoyue.common.base.ResponseCode;
import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.common.utils.DatetimeUtil;
import com.fuyunwang.chuoyue.common.utils.GlobalUtil;
import com.fuyunwang.chuoyue.common.utils.JwtTokenUtil;
import com.fuyunwang.chuoyue.security.filter.JwtAuthenticationTokenFilter;
import com.fuyunwang.chuoyue.security.filter.LoginFilter;
import com.fuyunwang.chuoyue.security.handler.ChuoyueAuthenticationFailureHandler;
import com.fuyunwang.chuoyue.security.handler.ChuoyueAuthenticationSuccessHandler;
import com.fuyunwang.chuoyue.security.mobile.SmsCodeAuthenticationSecurityConfig;
import com.fuyunwang.chuoyue.security.permission.ChuoyueAccessDecisionManager;
import com.fuyunwang.chuoyue.security.permission.ChuoyueSecurityMetadataSource;
import com.fuyunwang.chuoyue.security.service.ChuoyueUserDetailsService;
import com.fuyunwang.chuoyue.system.entity.TbAgent;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.AntPathMatcher;

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
 * @time: 2020/7/21 22:01
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class ChuoyueSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ChuoyueUserDetailsService userDetailsService;

    @Autowired
    private ChuoyueAccessDecisionManager chuoyueAccessDecisionManager;

    @Autowired
    private ChuoyueSecurityMetadataSource chuoyueSecurityMetadataSource;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ChuoyueAuthenticationSuccessHandler chuoyueAuthenticationSuccessHandler;
    @Autowired
    private ChuoyueAuthenticationFailureHandler chuoyueAuthenticationFailureHandler;


    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略classpath下的static目录的指定文件和文件夹,效率更高
        web.ignoring().antMatchers("/css/**", "/js/**", "/index.html", "/img/**", "/font/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                        o.setAccessDecisionManager(chuoyueAccessDecisionManager);
//                        o.setSecurityMetadataSource(chuoyueSecurityMetadataSource);
//                        return o;
//                    }
//                })
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/*/api-docs").permitAll()
                .antMatchers("/doc.html").permitAll()
                .antMatchers("/druid/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers("/captcha","/cyLogin","logout").permitAll()
                .antMatchers("/**/anon").permitAll()
                //.anyRequest().authenticated()
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
//                        TbAgent principal = (TbAgent) authentication.getPrincipal();
//                        principal.setAgentPassword(null);
                        out.write(new ObjectMapper().writeValueAsString(ResponseResult.createBySuccess(
                                ResponseCode.SUCCESS.getCode(),
                                "登出成功",
                                GlobalUtil.data("登出成功"))));
                        out.flush();
                        out.close();
                        //Todo 处理登出的逻辑

                    }
                })
                .permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        //认证失败 401
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                        PrintWriter out = httpServletResponse.getWriter();
                        ResponseResult responseResult =null;
                        String data = "";
                        if (e instanceof InsufficientAuthenticationException) {
                            data="请求失败，请联系管理员!";
                        }else{
                            data="用户认证失败";
                        }
                        responseResult=ResponseResult.createByError(
                                ResponseCode.AUTHENTICATION_FAILURE.getCode(),
                                ResponseCode.AUTHENTICATION_FAILURE.getDesc(),
                                GlobalUtil.data(data));
                        out.write(new ObjectMapper().writeValueAsString(responseResult));
                        out.flush();
                        out.close();
                    }
                })
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                        //权限拒绝403
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
                        PrintWriter out = httpServletResponse.getWriter();

                        out.write(new ObjectMapper().writeValueAsString(
                                ResponseResult.createByError(
                                        ResponseCode.ACCESS_DENIED.getCode(),
                                        ResponseCode.ACCESS_DENIED.getDesc(),
                                        GlobalUtil.data("对不起,您的请求被拒绝了")
                                )
                        ));
                        out.flush();
                        out.close();
                    }
                })
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .cors()
                .and()
                .headers()
                .frameOptions()
                .disable();
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationTokenFilter(),LoginFilter.class);
//        http.addFilterBefore(smsCodeAuthenticationTokenValidateFilter(),LoginFilter.class);
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    /*@Bean
    public SmsCodeAuthenticationTokenValidateFilter smsCodeAuthenticationTokenValidateFilter(){
        return new SmsCodeAuthenticationTokenValidateFilter();
    }*/

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationSuccessHandler(chuoyueAuthenticationSuccessHandler);
        loginFilter.setAuthenticationFailureHandler(chuoyueAuthenticationFailureHandler);
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/cyLogin");
        return loginFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Bean
    public AntPathMatcher antPathMatcher(){
        return new AntPathMatcher();
    }

}
