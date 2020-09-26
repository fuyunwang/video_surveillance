package com.fuyunwang.chuoyue.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fuyunwang.chuoyue.system.entity.MenuInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author FuyunWang
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单路径
     */
    private String menuPath;

    /**
     * 顺序
     */
    private Integer orders;

    /**
     * 子菜单项
     */
    private List<MenuInfoVo> children;
}
