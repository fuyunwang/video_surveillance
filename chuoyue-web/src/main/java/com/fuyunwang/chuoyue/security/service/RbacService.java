package com.fuyunwang.chuoyue.security.service;

import com.fuyunwang.chuoyue.system.entity.TbAcl;
import com.fuyunwang.chuoyue.system.entity.TbAgent;
import com.fuyunwang.chuoyue.system.mapper.TbAclMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: FuyunWang
 * @time: 2020/7/23 20:05
 */
@Slf4j
//@Primary
@Service("rbacService")
public class RbacService {
    @Autowired
    private AntPathMatcher antPathMatcher;

    @Autowired
    private TbAclMapper tbAclMapper;


//    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) { //首先判断先当前用户是否是我们UserDetails对象。
            String userName = ((TbAgent) principal).getAgentName();
            List<String> roles = ((TbAgent) principal).getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(roles)){
                return false;
            }
            // 数据库读取用户所拥有权限的所有URL
            List<TbAcl> tbacls = tbAclMapper.findAclUrlByRole(roles);
            try{
                if (CollectionUtils.isNotEmpty(tbacls)){
                    List<String> urls = tbacls.stream().filter(tbAcl -> tbAcl!=null).map(TbAcl::getAclUrl).collect(Collectors.toList());

                    // 注意这里不能用equal来判断，因为有些URL是有参数的，所以要用AntPathMatcher来比较
                    for (String url : urls) {
                        log.info(request.getRequestURI());
                        log.info(String.valueOf(request.getRequestURL()));
                        if (antPathMatcher.match(url, request.getRequestURI())) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                return hasPermission;
            }catch (Exception e){
                return hasPermission;
            }
        }
        return hasPermission;
    }
}
