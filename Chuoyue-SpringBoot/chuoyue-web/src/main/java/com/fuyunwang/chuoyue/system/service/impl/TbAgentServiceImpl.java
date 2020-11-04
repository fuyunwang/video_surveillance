package com.fuyunwang.chuoyue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyunwang.chuoyue.system.entity.TbAgent;
import com.fuyunwang.chuoyue.system.entity.TbDevice;
import com.fuyunwang.chuoyue.system.entity.TbMenuInfo;
import com.fuyunwang.chuoyue.system.mapper.TbAgentMapper;
import com.fuyunwang.chuoyue.system.service.ITbAgentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyunwang.chuoyue.system.service.ITbMenuInfoService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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

    @Override
    public IPage<TbAgent> getUsersByPage(Integer pagenum, Integer pagesize) {
        Page<TbAgent> page=new Page<>(pagenum,pagesize);
        List authorities = (List) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (CollectionUtils.isNotEmpty(authorities)){
            String roleName = (String) ((GrantedAuthority)(authorities.get(0))).getAuthority();
            if (roleName.equals("admin")){
                QueryWrapper<TbAgent> queryWrapper=new QueryWrapper<>();
                queryWrapper.orderByDesc("create_time");
                IPage<TbAgent> agentIPage = tbAgentMapper.selectPage(page, queryWrapper);
                return agentIPage;
            }
        }
        return null;
    }
}
