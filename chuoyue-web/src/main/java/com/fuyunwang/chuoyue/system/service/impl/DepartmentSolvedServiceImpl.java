package com.fuyunwang.chuoyue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyunwang.chuoyue.system.entity.Department;
import com.fuyunwang.chuoyue.system.entity.DepartmentSolved;
import com.fuyunwang.chuoyue.system.mapper.DepartmentSolvedMapper;
import com.fuyunwang.chuoyue.system.service.IDepartmentSolvedService;
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
 * @since 2020-10-12
 */
@Service
public class DepartmentSolvedServiceImpl extends ServiceImpl<DepartmentSolvedMapper, DepartmentSolved> implements IDepartmentSolvedService {

    @Autowired
    private DepartmentSolvedMapper departmentSolvedMapper;

    @Override
    public IPage<DepartmentSolved> getDepartmentsByPage(Integer pagenum, Integer pagesize) {
        Page<DepartmentSolved> page=new Page<>(pagenum,pagesize);
        List authorities = (List) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (CollectionUtils.isNotEmpty(authorities)){
            String roleName = (String) ((GrantedAuthority)(authorities.get(0))).getAuthority();
            if (roleName.equals("administrator")){
                IPage<DepartmentSolved> departmentIPage = departmentSolvedMapper.selectPage(page, new QueryWrapper<>());
                return departmentIPage;
            }
        }
        return null;
    }
}
