package com.fuyunwang.chuoyue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyunwang.chuoyue.common.exception.ParamException;
import com.fuyunwang.chuoyue.system.dto.DepartmentSolvedDto;
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
                QueryWrapper<DepartmentSolved> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("status",1);
                IPage<DepartmentSolved> departmentIPage = departmentSolvedMapper.selectPage(page, queryWrapper);
                return departmentIPage;
            }
        }
        return null;
    }

    @Override
    public DepartmentSolved getDepartmentSolvedById(Integer id) {
        QueryWrapper<DepartmentSolved> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("departmentId",id);
        List<DepartmentSolved> departmentSolveds = departmentSolvedMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(departmentSolveds)){
            return departmentSolveds.get(0);
        }
        throw new ParamException("当前没有数据");
    }

    @Override
    public String disposeAlarms(DepartmentSolvedDto departmentSolvedDto) {

        QueryWrapper<DepartmentSolved> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("departmentId",departmentSolvedDto.getDepartmentId());
        List<DepartmentSolved> departmentSolveds = departmentSolvedMapper.selectList(queryWrapper);
        for (DepartmentSolved departmentSolved : departmentSolveds) {
            departmentSolved.setContact(departmentSolvedDto.getContact());
            departmentSolved.setNote(departmentSolvedDto.getNote());
            departmentSolved.setStatus(1);
            departmentSolvedMapper.updateById(departmentSolved);
        }

        return "处置成功";
    }
}
