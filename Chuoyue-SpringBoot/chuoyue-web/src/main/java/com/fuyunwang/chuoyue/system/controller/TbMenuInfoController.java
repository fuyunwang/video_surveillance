package com.fuyunwang.chuoyue.system.controller;


import com.fuyunwang.chuoyue.common.base.ResponseCode;
import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.system.entity.TbMenuInfo;
import com.fuyunwang.chuoyue.system.service.ITbMenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FuyunWang
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/tb-menu-info")
public class TbMenuInfoController {

    @Autowired
    private ITbMenuInfoService iTbMenuInfoService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public ResponseResult<List<TbMenuInfo>> menuInfoList(@AuthenticationPrincipal UserDetails userDetails){
        List<TbMenuInfo> tbMenuInfos=iTbMenuInfoService.menuInfoList(userDetails);
        return ResponseResult.createBySuccess(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc(),tbMenuInfos);
    }
}
