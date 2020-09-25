package com.fuyunwang.chuoyue.system.controller;

import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.common.utils.GlobalUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @description: 先进行数据库查询代码校验然后进行方法级别注解权限
 * @author: FuyunWang
 * @time: 2020/7/23 18:57
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping(value = "/index/anon",method = RequestMethod.POST)
    public ResponseResult indexAnon(){
        return ResponseResult.createBySuccess("Hello Chuoyue", GlobalUtil.data("你好,绰约"));
    }

    @RequestMapping(value = "/index/lo",method = RequestMethod.POST)
    public ResponseResult indexLogin() {
        return ResponseResult.createBySuccess("Hello Chuoyue User", GlobalUtil.data("你好,绰约用户"));
    }

    @RequestMapping(value = "/index/ad",method = RequestMethod.POST)
    public ResponseResult indexAministrator(){
        return ResponseResult.createBySuccess("只有超管才能看到");
    }

    @RequestMapping(value = "/index/se",method = RequestMethod.POST)
    public ResponseResult indexSenior(){
        return ResponseResult.createBySuccess("高管和超管才能看到");
    }

    @RequestMapping(value = "/index/ju",method = RequestMethod.POST)
    public ResponseResult indexJunior(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseResult.createBySuccess("中管、高管、超管才能看到",GlobalUtil.data(userDetails.getUsername()));
    }


    //@PreAuthorize("hasAuthority('administrator')")
    @RequestMapping(value = "/index/test1",method = RequestMethod.POST)
    public ResponseResult indexTest1(){
        return ResponseResult.createBySuccess("测试注解和代码配合的权限校验1");
    }
    @PreAuthorize("hasAuthority('test2')")
    @RequestMapping(value = "/index/test2",method = RequestMethod.POST)
    public ResponseResult indexTest2(){
        return ResponseResult.createBySuccess("测试注解和代码配合的权限校验12");
    }
}
