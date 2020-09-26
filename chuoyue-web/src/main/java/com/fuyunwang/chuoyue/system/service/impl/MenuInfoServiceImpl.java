package com.fuyunwang.chuoyue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fuyunwang.chuoyue.system.entity.MenuInfo;
import com.fuyunwang.chuoyue.system.entity.TbRole;
import com.fuyunwang.chuoyue.system.mapper.MenuInfoMapper;
import com.fuyunwang.chuoyue.system.mapper.TbRoleMapper;
import com.fuyunwang.chuoyue.system.service.IMenuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyunwang.chuoyue.system.vo.MenuInfoVo;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-09-25
 */
@Service
public class MenuInfoServiceImpl extends ServiceImpl<MenuInfoMapper, MenuInfo> implements IMenuInfoService {

    @Autowired
    private TbRoleMapper tbRoleMapper;

    @Autowired
    private MenuInfoMapper menuInfoMapper;

    @Override
    public List<MenuInfoVo> getMenusListAll(UserDetails userDetails) {

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        TbRole tbRole=new TbRole();
        if (CollectionUtils.isNotEmpty(authorities)){
            GrantedAuthority next = authorities.iterator().next();
            String roleName = next.getAuthority();
            QueryWrapper<TbRole> tbRoleQueryWrapper=new QueryWrapper<>();
            tbRoleQueryWrapper.eq("role_name", roleName);
            tbRole = tbRoleMapper.selectOne(tbRoleQueryWrapper);
        }

        QueryWrapper<MenuInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.le("auth_type",tbRole.getType());
        queryWrapper.eq("parent_id",0);
        List<MenuInfo> menuInfos = menuInfoMapper.selectList(queryWrapper);
        List<MenuInfoVo> menuInfoVos= Lists.newArrayList();
        MenuInfoVo menuInfoVo;
        for (MenuInfo menuInfo: menuInfos){
            menuInfoVo=new MenuInfoVo();
            BeanUtils.copyProperties(menuInfo,menuInfoVo);


            List<MenuInfoVo> childMenuLists=Lists.newArrayList();
            getChildrenMenuList(childMenuLists,menuInfo.getId());

            menuInfoVo.setChildren(childMenuLists);

            menuInfoVos.add(menuInfoVo);
        }
        return menuInfoVos;
    }

    private void getChildrenMenuList(List list,Integer parentId){
        QueryWrapper<MenuInfo> menuInfoQueryWrapper=new QueryWrapper<>();
        menuInfoQueryWrapper.eq("parent_id",parentId);
        List<MenuInfo> menuInfos = menuInfoMapper.selectList(menuInfoQueryWrapper);
        if (CollectionUtils.isEmpty(menuInfos)){
            return;
        }
        menuInfos.forEach(menuInfo -> {
            MenuInfoVo menuInfoVo=new MenuInfoVo();
            BeanUtils.copyProperties(menuInfo,menuInfoVo);
            List<MenuInfoVo> childMenuLists=Lists.newArrayList();
            getChildrenMenuList(childMenuLists,menuInfo.getId());
            menuInfoVo.setChildren(childMenuLists);
            list.add(menuInfoVo);
        });

    }
}
