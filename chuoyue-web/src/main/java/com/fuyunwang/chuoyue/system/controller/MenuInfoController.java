package com.fuyunwang.chuoyue.system.controller;


import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.system.service.IMenuInfoService;
import com.fuyunwang.chuoyue.system.vo.MenuInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author FuyunWang
 * @since 2020-09-25
 */
@RestController
@RequestMapping("/menus")
public class MenuInfoController {

    @Autowired
    private IMenuInfoService iMenuInfoService;

    @RequestMapping("/listall")
    public ResponseResult<List<MenuInfoVo>> getMenusListAll(@AuthenticationPrincipal UserDetails userDetails){
        List<MenuInfoVo> menuInfoVos=iMenuInfoService.getMenusListAll(userDetails);
        return ResponseResult.createBySuccess(menuInfoVos);
    }
}
