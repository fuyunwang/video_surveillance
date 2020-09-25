package com.fuyunwang.chuoyue.system.mapper;

import com.fuyunwang.chuoyue.system.entity.TbAgent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fuyunwang.chuoyue.system.entity.TbRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author FuyunWang
 * @since 2020-07-21
 */
public interface TbAgentMapper extends BaseMapper<TbAgent> {
    TbAgent findByAgentname(@Param(value = "agentName") String agentName);
    TbAgent findByAgentphone(@Param(value = "agentPhone") String agentPhone);
    List<TbRole> findRolesByAgentname(@Param(value = "agentName") String agentName);
}
