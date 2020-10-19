package com.fuyunwang.chuoyue.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fuyunwang.chuoyue.system.dto.DepartmentSolvedDto;
import com.fuyunwang.chuoyue.system.entity.TbDepartmentSolved;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-10-19
 */
public interface ITbDepartmentSolvedService extends IService<TbDepartmentSolved> {

    String disposeAlarms(DepartmentSolvedDto departmentSolvedDto);

    TbDepartmentSolved getDepartmentSolvedById(Integer id);

    IPage<TbDepartmentSolved> getDepartmentsByPage(Integer pagenum, Integer pagesize);

    TbDepartmentSolved getDepartmentSolvedOneById(Integer id);
}
