package com.fuyunwang.chuoyue.security.permission;

import com.fuyunwang.chuoyue.system.entity.TbAcl;
import com.fuyunwang.chuoyue.system.mapper.TbAclMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @description:
 * @author: FuyunWang
 * @time: 2020/7/22 20:16
 */
@Component
public class ChuoyueSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private TbAclMapper tbAclMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl=((FilterInvocation)object).getRequestUrl();
        if(StringUtils.equals(requestUrl, "/cyLogin")|| StringUtils.equals(requestUrl, "/registry")) {
            //登录请求任何权限都可以访问
            return null;
        }
//        TbAcl tbAcl = tbAclMapper.findByAclUrl(requestUrl);
//        if (CollectionUtils.isNotEmpty(tbAcl.getRoleList())){
//            int size = tbAcl.getRoleList().size();
//            String[] values=new String[size];
//            for(int i=0;i<size;i++) {
//                values[i]=tbAcl.getRoleList().get(i).getRoleName();
//            }
//            return SecurityConfig.createList(values);
//            return null;
//        }
        //url就不需要任何角色就能访问
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
