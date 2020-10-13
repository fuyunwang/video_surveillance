package com.fuyunwang.chuoyue.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.system.dto.DepartmentSolvedDto;
import com.fuyunwang.chuoyue.system.entity.Department;
import com.fuyunwang.chuoyue.system.entity.DepartmentSolved;
import com.fuyunwang.chuoyue.system.service.IDepartmentService;
import com.fuyunwang.chuoyue.system.service.IDepartmentSolvedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @RequestMapping(value = "/getbyid",method = RequestMethod.POST)
    public ResponseResult<DepartmentSolved> getDepartmentSolvedById(@RequestParam("id")Integer id){
        DepartmentSolved departmentSolved=iDepartmentService.getDepartmentSolvedById(id);
        return ResponseResult.createBySuccess(departmentSolved);
    }

    @RequestMapping(value = "/dispose",method = RequestMethod.POST)
    public ResponseResult disposeAlarms(@Valid @RequestBody DepartmentSolvedDto departmentSolvedDto){
        String result=iDepartmentService.disposeAlarms(departmentSolvedDto);
        return ResponseResult.createBySuccess(result);
    }
}
