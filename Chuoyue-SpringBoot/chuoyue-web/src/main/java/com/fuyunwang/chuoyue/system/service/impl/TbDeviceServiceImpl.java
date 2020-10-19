package com.fuyunwang.chuoyue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyunwang.chuoyue.system.entity.TbDevice;
import com.fuyunwang.chuoyue.system.mapper.TbDeviceMapper;
import com.fuyunwang.chuoyue.system.service.ITbDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-10-19
 */
@Service
public class TbDeviceServiceImpl extends ServiceImpl<TbDeviceMapper, TbDevice> implements ITbDeviceService {

    @Autowired
    private TbDeviceMapper tbDeviceMapper;

    @Override
    public IPage<TbDevice> getDevicesByPage(Integer pagenum, Integer pagesize) {
        Page<TbDevice> page=new Page<>(pagenum,pagesize);
        List authorities = (List) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (CollectionUtils.isNotEmpty(authorities)){
            String roleName = (String) ((GrantedAuthority)(authorities.get(0))).getAuthority();
            if (roleName.equals("admin")){
                IPage<TbDevice> departmentIPage = tbDeviceMapper.selectPage(page, new QueryWrapper<>());
                return departmentIPage;
            }
        }
        return null;
    }
}
