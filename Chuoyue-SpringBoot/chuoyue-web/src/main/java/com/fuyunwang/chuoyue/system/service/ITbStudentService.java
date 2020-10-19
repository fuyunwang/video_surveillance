package com.fuyunwang.chuoyue.system.service;

import com.fuyunwang.chuoyue.system.entity.TbStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-07-21
 */
public interface ITbStudentService extends IService<TbStudent> {
    ModelAndView exportAllStudentsInfoExcel();
}
