package com.fuyunwang.chuoyue.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fuyunwang.chuoyue.system.entity.DepartmentSolved;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-10-12
 */
public interface IDepartmentSolvedService extends IService<DepartmentSolved> {

    IPage<DepartmentSolved> getDepartmentsByPage(Integer pagenum, Integer pagesize);
}
