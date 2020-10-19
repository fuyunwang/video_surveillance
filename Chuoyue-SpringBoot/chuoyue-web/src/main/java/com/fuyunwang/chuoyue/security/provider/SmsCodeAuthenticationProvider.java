package com.fuyunwang.chuoyue.security.provider;

import com.fuyunwang.chuoyue.common.exception.BadMobileCodeException;
import com.fuyunwang.chuoyue.common.exception.MobileNotFoundException;
import com.fuyunwang.chuoyue.security.mobile.SmsCodeAuthenticationToken;
import com.fuyunwang.chuoyue.security.service.ChuoyueUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * @description:
 * @author: FuyunWang
 * @time: 2020/7/25 14:14
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsCodeAuthenticationProvider.class);

    private ChuoyueUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
        String principal = (String) smsCodeAuthenticationToken.getPrincipal();//此处是手机号
        String smsCode = (String) smsCodeAuthenticationToken.getSmsCode();//此处是手机验证码
        UserDetails userDetails = userDetailsService.loadUserByMobile(principal);
        if (null==userDetails){
            LOGGER.error("当前手机号未注册");
            throw new MobileNotFoundException("当前手机号未注册");
        }
        //校验验证码,结合redis来做
        if (!smsCode.equals("970629")){
            LOGGER.error("当前手机号验证码错误了");
            throw new BadMobileCodeException("当前手机号验证码错误");
        }

        SmsCodeAuthenticationToken authenticationResult=new SmsCodeAuthenticationToken(userDetails,userDetails.getAuthorities());
        authenticationResult.setDetails(smsCodeAuthenticationToken.getDetails());//生成新token并返回
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public ChuoyueUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(ChuoyueUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
