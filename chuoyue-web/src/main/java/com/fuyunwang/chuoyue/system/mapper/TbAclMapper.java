package com.fuyunwang.chuoyue.system.mapper;

import com.fuyunwang.chuoyue.system.entity.TbAcl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author FuyunWang
 * @since 2020-07-21
 */
public interface TbAclMapper extends BaseMapper<TbAcl> {
    //TbAcl findByAclUrl(String acl);
    List<TbAcl> findAclUrlByRole(List<String> roleName);
}