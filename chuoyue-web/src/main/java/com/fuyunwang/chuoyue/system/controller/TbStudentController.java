package com.fuyunwang.chuoyue.system.controller;


import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.common.utils.GlobalUtil;
import com.fuyunwang.chuoyue.system.service.ITbStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FuyunWang
 * @since 2020-07-21
 */
@RestController
@RequestMapping("/tb-student")
public class TbStudentController {

    @Autowired
    @Qualifier(value = "tbStudentServiceImpl")
    private ITbStudentService iTbStudentService;

    @PreAuthorize("hasAuthority('administrator')")
    @RequestMapping(value = "/index/ad",method = RequestMethod.POST)
    public ResponseResult indexAministrator(){
        return ResponseResult.createBySuccess("只有超管才能看到");
    }

    @PreAuthorize("hasAnyAuthority('administrator','senior')")
    @RequestMapping(value = "/index/se",method = RequestMethod.POST)
    public ResponseResult indexSenior(){
        return ResponseResult.createBySuccess("高管和超管才能看到");
    }

    @PreAuthorize("hasAnyAuthority('administrator','senior','junior')")
    @RequestMapping(value = "/index/ju",method = RequestMethod.POST)
    public ResponseResult indexJunior(){
        return ResponseResult.createBySuccess("中管、高管、超管才能看到");
    }

    @RequestMapping(value = "/index/lo",method = RequestMethod.POST)
    public ResponseResult indexLogin(@AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return ResponseResult.createBySuccess("登陆就能看到", GlobalUtil.data(username+authorities.toString()));
    }

    @RequestMapping(value = "/index/anon",method = RequestMethod.POST)
    public ResponseResult indexAnon(){
        return ResponseResult.createBySuccess("不登陆也能看到");
    }

    @RequestMapping(value = "/excel/export/anon",method = RequestMethod.GET)
    public ModelAndView exportAllStudentsInfoExcel(){
        return iTbStudentService.exportAllStudentsInfoExcel();
    }
}
