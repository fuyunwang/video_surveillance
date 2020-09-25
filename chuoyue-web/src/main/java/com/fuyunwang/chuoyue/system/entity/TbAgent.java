package com.fuyunwang.chuoyue.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbAgent implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("agent_achieve")
    private Integer agentAchieve;

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

    @TableField("role_id")
    private Integer roleId;

    @TableField(exist = false)
    private List<TbRole> roleList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(roleList.size());
        for (TbRole role : roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
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
}
