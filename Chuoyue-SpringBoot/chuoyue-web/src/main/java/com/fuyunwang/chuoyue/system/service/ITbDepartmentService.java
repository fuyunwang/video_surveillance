package com.fuyunwang.chuoyue.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fuyunwang.chuoyue.system.entity.TbDepartment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-10-19
 */
public interface ITbDepartmentService extends IService<TbDepartment> {

    IPage<TbDepartment> getDepartmentsByPage(Integer pagenum, Integer pagesize);
}
