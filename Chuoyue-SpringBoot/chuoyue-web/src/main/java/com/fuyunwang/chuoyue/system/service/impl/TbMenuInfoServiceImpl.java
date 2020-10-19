package com.fuyunwang.chuoyue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fuyunwang.chuoyue.system.entity.TbAgent;
import com.fuyunwang.chuoyue.system.entity.TbMenuInfo;
import com.fuyunwang.chuoyue.system.entity.TbMenuInfoMeta;
import com.fuyunwang.chuoyue.system.mapper.TbMenuInfoMapper;
import com.fuyunwang.chuoyue.system.mapper.TbMenuInfoMetaMapper;
import com.fuyunwang.chuoyue.system.service.ITbMenuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-10-17
 */
@Slf4j
@Service
public class TbMenuInfoServiceImpl extends ServiceImpl<TbMenuInfoMapper, TbMenuInfo> implements ITbMenuInfoService {

    @Autowired
    private TbMenuInfoMapper menuInfoMapper;

    @Autowired
    private TbMenuInfoMetaMapper menuInfoMetaMapper;

    @Override
    public List<TbMenuInfo> menuInfoList(UserDetails userDetails) {
        if (userDetails instanceof TbAgent){
            TbAgent tbAgent= (TbAgent) userDetails;
            log.info(tbAgent.toString());
            if (CollectionUtils.isNotEmpty(Arrays.asList(tbAgent.getRoles()))){
                if (Arrays.asList(tbAgent.getRoles()).contains("admin")){
                    QueryWrapper<TbMenuInfo> queryWrapper=new QueryWrapper<>();
                    queryWrapper.eq("parent_id",0);
                    queryWrapper.orderByAsc("orders");
                    List<TbMenuInfo> tbMenuInfos = menuInfoMapper.selectList(queryWrapper);
                    List<TbMenuInfo> results=Lists.newArrayList();

                    for (TbMenuInfo tbMenuInfo:tbMenuInfos){
                        addMenuInfoMeta(tbMenuInfo);
                        findAllMenuInfoRecur(tbMenuInfo);
                        results.add(tbMenuInfo);
                    }


                    return tbMenuInfos;
                }else if(Arrays.asList(tbAgent.getRoles()).contains("senior")){

                }else if(Arrays.asList(tbAgent.getRoles()).contains("junior")){

                }
            }
        }
        return null;
    }

    private void findAllMenuInfoRecur(TbMenuInfo parent) {
        QueryWrapper<TbMenuInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("parent_id",parent.getId());
        queryWrapper.orderByAsc("orders");
        List<TbMenuInfo> tbMenuInfos = menuInfoMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(tbMenuInfos)){
            return;
        }
        parent.setChildren(tbMenuInfos);
        for (TbMenuInfo tbMenuInfo:tbMenuInfos){
            addMenuInfoMeta(tbMenuInfo);
            findAllMenuInfoRecur(tbMenuInfo);
        }
    }

    public void addMenuInfoMeta(TbMenuInfo tbMenuInfo){
            QueryWrapper<TbMenuInfoMeta> q=new QueryWrapper<>();
            q.eq("menu_id",tbMenuInfo.getId());
            TbMenuInfoMeta tbMenuInfoMeta = menuInfoMetaMapper.selectOne(q);
            tbMenuInfo.setMeta(tbMenuInfoMeta);

    }

}
