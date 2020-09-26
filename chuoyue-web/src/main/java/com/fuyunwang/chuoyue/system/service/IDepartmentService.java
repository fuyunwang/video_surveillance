package com.fuyunwang.chuoyue.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fuyunwang.chuoyue.system.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-09-25
 */
public interface IDepartmentService extends IService<Department> {

    IPage<Department> getDepartmentsByPage(Integer pagenum, Integer pagesize);
}
