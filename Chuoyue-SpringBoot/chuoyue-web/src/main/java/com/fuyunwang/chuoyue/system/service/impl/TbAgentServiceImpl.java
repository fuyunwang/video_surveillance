package com.fuyunwang.chuoyue.system.service.impl;

import com.fuyunwang.chuoyue.system.entity.TbAgent;
import com.fuyunwang.chuoyue.system.entity.TbMenuInfo;
import com.fuyunwang.chuoyue.system.mapper.TbAgentMapper;
import com.fuyunwang.chuoyue.system.service.ITbAgentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyunwang.chuoyue.system.service.ITbMenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-07-21
 */
@Service
public class TbAgentServiceImpl extends ServiceImpl<TbAgentMapper, TbAgent> implements ITbAgentService {

    @Autowired
    private TbAgentMapper tbAgentMapper;
    @Autowired
    private ITbMenuInfoService iTbMenuInfoService;

    @Override
    public TbAgent getAllAgentInfoById(Integer id) {
        TbAgent tbAgent=tbAgentMapper.getAllAgentInfoById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<TbMenuInfo> tbMenuInfos = iTbMenuInfoService.menuInfoList((UserDetails) authentication.getPrincipal());
        tbAgent.setMenus(tbMenuInfos);
        return tbAgent;
    }
}
