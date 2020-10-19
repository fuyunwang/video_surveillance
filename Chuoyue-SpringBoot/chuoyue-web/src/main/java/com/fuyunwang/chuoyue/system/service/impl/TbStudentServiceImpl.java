package com.fuyunwang.chuoyue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fuyunwang.chuoyue.system.entity.TbStudent;
import com.fuyunwang.chuoyue.system.mapper.TbStudentMapper;
import com.fuyunwang.chuoyue.system.service.ITbStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-07-21
 */
@Service
public class TbStudentServiceImpl extends ServiceImpl<TbStudentMapper, TbStudent> implements ITbStudentService {

    @Autowired
    private TbStudentMapper tbStudentMapper;

    @Override
    public ModelAndView exportAllStudentsInfoExcel() {
        List<TbStudent> tbStudents = tbStudentMapper.selectList(new QueryWrapper<>());
        ModelAndView modelAndView=new ModelAndView(new JeecgEntityExcelView());
        modelAndView.addObject(NormalExcelConstants.CLASS,TbStudent.class);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST,tbStudents);
        modelAndView.addObject(NormalExcelConstants.FILE_NAME,"StudentInfo");
        modelAndView.addObject(NormalExcelConstants.PARAMS, new ExportParams("StudentInfo报表","导出信息"));
        return modelAndView;
    }
}
