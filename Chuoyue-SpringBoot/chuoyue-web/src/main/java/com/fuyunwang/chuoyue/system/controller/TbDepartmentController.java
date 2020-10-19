package com.fuyunwang.chuoyue.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.system.entity.TbDepartment;
import com.fuyunwang.chuoyue.system.service.ITbDepartmentService;
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
 * @since 2020-10-19
 */
@RestController
@RequestMapping("/tb-department")
public class TbDepartmentController {
    @Autowired
    private ITbDepartmentService iDepartmentService;

    @RequestMapping(value="/getbypage",method = RequestMethod.POST)
    public ResponseResult<IPage<TbDepartment>> getDepartmentsByPage(@RequestParam("pagenum")Integer pagenum, @RequestParam("pagesize")Integer pagesize){
        IPage<TbDepartment> departmentIPage=iDepartmentService.getDepartmentsByPage(pagenum,pagesize);
        return ResponseResult.createBySuccess(departmentIPage);
    }
}
