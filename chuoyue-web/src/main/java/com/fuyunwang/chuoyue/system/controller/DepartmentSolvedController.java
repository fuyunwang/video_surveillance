package com.fuyunwang.chuoyue.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.system.entity.Department;
import com.fuyunwang.chuoyue.system.entity.DepartmentSolved;
import com.fuyunwang.chuoyue.system.service.IDepartmentService;
import com.fuyunwang.chuoyue.system.service.IDepartmentSolvedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FuyunWang
 * @since 2020-10-12
 */
@RestController
@RequestMapping("/department-solved")
public class DepartmentSolvedController {
    @Autowired
    private IDepartmentSolvedService iDepartmentService;

    @RequestMapping(value="/getbypage",method = RequestMethod.POST)
    public ResponseResult<IPage<DepartmentSolved>> getDepartmentsByPage(@RequestParam("pagenum")Integer pagenum, @RequestParam("pagesize")Integer pagesize){
        IPage<DepartmentSolved> departmentIPage=iDepartmentService.getDepartmentsByPage(pagenum,pagesize);
        return ResponseResult.createBySuccess(departmentIPage);
    }
}
