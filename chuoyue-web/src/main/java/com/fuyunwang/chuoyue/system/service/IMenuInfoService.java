package com.fuyunwang.chuoyue.system.service;

import com.fuyunwang.chuoyue.system.entity.MenuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fuyunwang.chuoyue.system.vo.MenuInfoVo;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-09-25
 */
public interface IMenuInfoService extends IService<MenuInfo> {
    List<MenuInfoVo> getMenusListAll(UserDetails userDetails);
}
