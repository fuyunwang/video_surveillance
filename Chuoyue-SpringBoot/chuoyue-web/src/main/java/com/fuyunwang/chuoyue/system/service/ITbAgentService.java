package com.fuyunwang.chuoyue.system.service;

import com.fuyunwang.chuoyue.system.entity.TbAgent;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-07-21
 */
public interface ITbAgentService extends IService<TbAgent> {
    TbAgent getAllAgentInfoById(Integer id);
}
