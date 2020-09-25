package com.fuyunwang.chuoyue.system.service.impl;

import com.fuyunwang.chuoyue.system.entity.Employee;
import com.fuyunwang.chuoyue.system.mapper.EmployeeMapper;
import com.fuyunwang.chuoyue.system.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-09-25
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
