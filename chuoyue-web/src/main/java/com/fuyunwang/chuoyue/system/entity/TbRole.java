package com.fuyunwang.chuoyue.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class TbRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("operator")
    private String operator;

    @TableField("remark")
    private String remark;

    @TableField("role_name")
    private String roleName;

    @TableField("status")
    private Integer status;

    @TableField("type")
    private Integer type;

    @TableField("update_time")
    private LocalDateTime updateTime;


}
