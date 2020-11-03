package com.fuyunwang.chuoyue.system.controller;


import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.system.entity.TbAgent;
import com.fuyunwang.chuoyue.system.service.ITbAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FuyunWang
 * @since 2020-07-21
 */
@RestController
@RequestMapping("/tb-agent")
public class TbAgentController {

    @Autowired
    private ITbAgentService iTbAgentService;

    @RequestMapping(value = "/info")
    public ResponseResult<TbAgent> getUserInfo(@AuthenticationPrincipal UserDetails userDetails){
        TbAgent principal= (TbAgent) userDetails;
        TbAgent tbAgent=iTbAgentService.getAllAgentInfoById(principal.getId());
        return ResponseResult.createBySuccess("获取成功",tbAgent);
    }

//    @RequestMapping(value = "/portal-base-info")
//    public ResponseResult<>

}
