package com.fuyunwang.chuoyue.security.mobile;

import com.fuyunwang.chuoyue.security.filter.LoginFilter;
import com.fuyunwang.chuoyue.security.filter.SmsCodeAuthenticationTokenFilter;
import com.fuyunwang.chuoyue.security.handler.ChuoyueAuthenticationFailureHandler;
import com.fuyunwang.chuoyue.security.handler.ChuoyueAuthenticationSuccessHandler;
import com.fuyunwang.chuoyue.security.provider.SmsCodeAuthenticationProvider;
import com.fuyunwang.chuoyue.security.service.ChuoyueUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;

/**
 * @description:
 * @author: FuyunWang
 * @time: 2020/7/25 14:42
 */
@Configuration
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private ChuoyueUserDetailsService userDetailsService;
    @Autowired
    private ChuoyueAuthenticationSuccessHandler chuoyueAuthenticationSuccessHandler;
    @Autowired
    private ChuoyueAuthenticationFailureHandler chuoyueAuthenticationFailureHandler;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationTokenFilter smsCodeAuthenticationTokenFilter=new SmsCodeAuthenticationTokenFilter();
        smsCodeAuthenticationTokenFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        //设置handler
        smsCodeAuthenticationTokenFilter.setAuthenticationSuccessHandler(chuoyueAuthenticationSuccessHandler);
        smsCodeAuthenticationTokenFilter.setAuthenticationFailureHandler(chuoyueAuthenticationFailureHandler);
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider=new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
        http.authenticationProvider(smsCodeAuthenticationProvider).addFilterAfter(smsCodeAuthenticationTokenFilter, LoginFilter.class);
    }
}
