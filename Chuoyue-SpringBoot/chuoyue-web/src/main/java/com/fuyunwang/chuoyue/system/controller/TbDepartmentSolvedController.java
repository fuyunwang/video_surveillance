package com.fuyunwang.chuoyue.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.system.dto.DepartmentSolvedDto;
import com.fuyunwang.chuoyue.system.entity.TbDepartmentSolved;
import com.fuyunwang.chuoyue.system.service.ITbDepartmentSolvedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FuyunWang
 * @since 2020-10-19
 */
@RestController
@RequestMapping("/tb-department-solved")
public class TbDepartmentSolvedController {
    @Autowired
    private ITbDepartmentSolvedService iDepartmentService;

    @RequestMapping(value="/getbypage",method = RequestMethod.POST)
    public ResponseResult<IPage<TbDepartmentSolved>> getDepartmentsByPage(@RequestParam("pagenum")Integer pagenum, @RequestParam("pagesize")Integer pagesize){
        IPage<TbDepartmentSolved> departmentIPage=iDepartmentService.getDepartmentsByPage(pagenum,pagesize);
        return ResponseResult.createBySuccess(departmentIPage);
    }

    @RequestMapping(value = "/getbyid",method = RequestMethod.POST)
    public ResponseResult<TbDepartmentSolved> getDepartmentSolvedById(@RequestParam("id")Integer id){
        TbDepartmentSolved departmentSolved=iDepartmentService.getDepartmentSolvedById(id);
        return ResponseResult.createBySuccess(departmentSolved);
    }

    @RequestMapping(value = "/dispose",method = RequestMethod.POST)
    public ResponseResult disposeAlarms(@Valid @RequestBody DepartmentSolvedDto departmentSolvedDto){
        String result=iDepartmentService.disposeAlarms(departmentSolvedDto);
        return ResponseResult.createBySuccess(result);
    }

    @RequestMapping(value = "/getone",method = RequestMethod.POST)
    public ResponseResult<TbDepartmentSolved> getDepartmentSolvedOneById(@RequestParam("id")Integer id){
        return ResponseResult.createBySuccess(iDepartmentService.getDepartmentSolvedOneById(id));
    }
}
