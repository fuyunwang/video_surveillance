package com.fuyunwang.chuoyue.security.filter;

import com.fuyunwang.chuoyue.security.mobile.SmsCodeAuthenticationToken;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: FuyunWang
 * @time: 2020/7/25 14:06
 */
public class SmsCodeAuthenticationTokenFilter extends
        AbstractAuthenticationProcessingFilter {
    // ~ Static fields/initializers
    // =====================================================================================

    public static final String CHUOYUE_FORM_MOBILE_KEY = "mobile";
    public static final String CHUOYUE_FORM_MOBILE_CODE = "sms_code";
    private String mobileParameter = CHUOYUE_FORM_MOBILE_KEY;//请求中携带参数的参数名
    private String smsCodeParameter = CHUOYUE_FORM_MOBILE_CODE;//请求中携带参数的参数名
    private boolean postOnly = true;

    // ~ Constructors
    // ===================================================================================================

    public SmsCodeAuthenticationTokenFilter() {
        super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
    }

    // ~ Methods
    // ========================================================================================================

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String mobile = obtainMobile(request);
        String smsCode = obtainSmsCode(request);
        if (mobile == null) {
            mobile = "";
        }
        if (smsCode == null) {
            smsCode = "";
        }


        mobile = mobile.trim();
        smsCode = smsCode.trim();

        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile,smsCode);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * Enables subclasses to override the composition of the username, such as by
     * including additional values and a separator.
     *
     * @param request so that request attributes can be retrieved
     *
     * @return the mobile that will be presented in the <code>Authentication</code>
     * request token to the <code>AuthenticationManager</code>
     */
    @Nullable
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }
    /**
     * Enables subclasses to override the composition of the password, such as by
     * including additional values and a separator.
     * <p>
     * This might be used for example if a postcode/zipcode was required in addition to
     * the password. A delimiter such as a pipe (|) should be used to separate the
     * password and extended value(s). The <code>AuthenticationDao</code> will need to
     * generate the expected password in a corresponding manner.
     * </p>
     *
     * @param request so that request attributes can be retrieved
     *
     * @return the smsCode that will be presented in the <code>Authentication</code>
     * request token to the <code>AuthenticationManager</code>
     */
    @Nullable
    protected String obtainSmsCode(HttpServletRequest request) {
        return request.getParameter(smsCodeParameter);
    }

    /**
     * Provided so that subclasses may configure what is put into the authentication
     * request's details property.
     *
     * @param request that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details
     * set
     */
    protected void setDetails(HttpServletRequest request,
                              SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * Sets the parameter name which will be used to obtain the username from the login
     * request.
     *
     * @param mobileParameter the parameter name. Defaults to "mobile".
     */
    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "Mobile parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }
    /**
     * Sets the parameter name which will be used to obtain the password from the login
     * request..
     *
     * @param smsCodeParameter the parameter name. Defaults to "smsCode".
     */
    public void setPasswordParameter(String smsCodeParameter) {
        Assert.hasText(smsCodeParameter, "smsCode parameter must not be empty or null");
        this.smsCodeParameter = smsCodeParameter;
    }

    /**
     * Defines whether only HTTP POST requests will be allowed by this filter. If set to
     * true, and an authentication request is received which is not a POST request, an
     * exception will be raised immediately and authentication will not be attempted. The
     * <tt>unsuccessfulAuthentication()</tt> method will be called as if handling a failed
     * authentication.
     * <p>
     * Defaults to <tt>true</tt> but may be overridden by subclasses.
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getMobileParameter() {
        return mobileParameter;
    }

    public final String getSmsCodeParameter() {
        return smsCodeParameter;
    }
}
