package com.fuyunwang.chuoyue.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author FuyunWang
 * @since 2020-07-21
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbAgent implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("agent_motto")
    private String agentMotto;

    @TableField("agent_email")
    private String agentEmail;

    @TableField("agent_idcard")
    private String agentIdcard;

    @TableField("agent_idcard_img")
    private String agentIdcardImg;

    @TableField("agent_name")
    private String agentName;

    @TableField("agent_password")
    private String agentPassword;

    @TableField("agent_phone")
    private String agentPhone;

    @TableField("agent_school")
    private String agentSchool;

    @TableField("parent_id")
    private Integer parentId;

    @TableField("status")
    private Integer status;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

//    @TableField("role_id")
//    private Integer roleId;

//    @JsonIgnore
    @TableField(exist = false)
    private List<TbRole> roleList;

    @JsonIgnore
//    @JsonProperty("roles")
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (CollectionUtils.isNotEmpty(roleList)){
            List<SimpleGrantedAuthority> authorities = new ArrayList<>(roleList.size());
            for (TbRole role : roleList) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            return authorities;
        }
        return null;
    }

    @Override
    public String getPassword() {
        return this.agentPassword;
    }

    @Override
    public String getUsername() {
        return this.agentName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbAgent agent = (TbAgent) o;
        return Objects.equals(getAgentName(), agent.agentName);
    }

    public String[] getRoles(){
       if (getAuthorities()!=null){
           String[] roles=new String[getAuthorities().size()];
           Iterator<? extends GrantedAuthority> iterator = getAuthorities().iterator();
           int i = 0;
           while (iterator.hasNext()){
               roles[i++]= String.valueOf(iterator.next());
           }
           return roles;
       }
       return null;
    }

    @TableField(exist = false)
    private List<TbMenuInfo> menus;
}
