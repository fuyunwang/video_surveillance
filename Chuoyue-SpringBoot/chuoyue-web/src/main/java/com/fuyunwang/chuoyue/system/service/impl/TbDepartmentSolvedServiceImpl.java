package com.fuyunwang.chuoyue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyunwang.chuoyue.common.exception.ParamException;
import com.fuyunwang.chuoyue.system.dto.DepartmentSolvedDto;
import com.fuyunwang.chuoyue.system.entity.TbDepartmentSolved;
import com.fuyunwang.chuoyue.system.mapper.TbDepartmentSolvedMapper;
import com.fuyunwang.chuoyue.system.service.ITbDepartmentSolvedService;
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
public class TbDepartmentSolvedServiceImpl extends ServiceImpl<TbDepartmentSolvedMapper, TbDepartmentSolved> implements ITbDepartmentSolvedService {
    @Autowired
    private TbDepartmentSolvedMapper departmentSolvedMapper;

    @Override
    public IPage<TbDepartmentSolved> getDepartmentsByPage(Integer pagenum, Integer pagesize) {
        Page<TbDepartmentSolved> page=new Page<>(pagenum,pagesize);
        List authorities = (List) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (CollectionUtils.isNotEmpty(authorities)){
            String roleName = (String) ((GrantedAuthority)(authorities.get(0))).getAuthority();
            if (roleName.equals("administrator")){
                QueryWrapper<TbDepartmentSolved> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("status",1);
                IPage<TbDepartmentSolved> departmentIPage = departmentSolvedMapper.selectPage(page, queryWrapper);
                return departmentIPage;
            }
        }
        return null;
    }

    @Override
    public TbDepartmentSolved getDepartmentSolvedById(Integer id) {
        QueryWrapper<TbDepartmentSolved> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("departmentId",id);
        List<TbDepartmentSolved> departmentSolveds = departmentSolvedMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(departmentSolveds)){
            return departmentSolveds.get(0);
        }
        throw new ParamException("当前没有数据");
    }

    @Override
    public String disposeAlarms(DepartmentSolvedDto departmentSolvedDto) {

        QueryWrapper<TbDepartmentSolved> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("departmentId",departmentSolvedDto.getDepartmentId());
        List<TbDepartmentSolved> departmentSolveds = departmentSolvedMapper.selectList(queryWrapper);
        for (TbDepartmentSolved departmentSolved : departmentSolveds) {
            departmentSolved.setContact(departmentSolvedDto.getContact());
            departmentSolved.setNote(departmentSolvedDto.getNote());
            departmentSolved.setStatus(1);
            departmentSolvedMapper.updateById(departmentSolved);
        }

        return "处置成功";
    }

    @Override
    public TbDepartmentSolved getDepartmentSolvedOneById(Integer id) {
        TbDepartmentSolved departmentSolved = departmentSolvedMapper.selectById(id);
        return departmentSolved;
    }
}
