package com.fuyunwang.chuoyue.system.service;

import com.fuyunwang.chuoyue.system.entity.TbMenuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-10-17
 */
public interface ITbMenuInfoService extends IService<TbMenuInfo> {
    List<TbMenuInfo> menuInfoList(UserDetails userDetails);
}
