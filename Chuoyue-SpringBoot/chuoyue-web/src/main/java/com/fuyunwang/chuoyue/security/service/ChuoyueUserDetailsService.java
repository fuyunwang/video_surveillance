package com.fuyunwang.chuoyue.security.service;

import com.fuyunwang.chuoyue.common.exception.MobileNotFoundException;
import com.fuyunwang.chuoyue.system.entity.TbAgent;
import com.fuyunwang.chuoyue.system.entity.TbRole;
import com.fuyunwang.chuoyue.system.mapper.TbAgentMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: FuyunWang
 * @time: 2020/7/22 19:09
 */
@Service
public class ChuoyueUserDetailsService implements UserDetailsService {

    @Autowired
    private TbAgentMapper tbAgentMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbAgent agent = tbAgentMapper.findByAgentname(username);
        if (agent==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<TbRole> roles = tbAgentMapper.findRolesByAgentname(agent.getAgentName());
        agent.setRoleList(roles);
        return agent;
    }

    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        TbAgent agent = tbAgentMapper.findByAgentphone(mobile);
        if (agent==null){
            throw new MobileNotFoundException("手机号不存在");
        }
        List<TbRole> roles = tbAgentMapper.findRolesByAgentname(agent.getAgentName());
        agent.setRoleList(roles);
        return agent;
    }
}
