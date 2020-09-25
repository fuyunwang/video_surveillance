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
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名
     */
    @TableField("departmentName")
    private String departmentName;

    /**
     * 报警时间
     */
    @TableField("alarmTime")
    private LocalDateTime alarmTime;

    /**
     * 事件类型
     */
    @TableField("incidentType")
    private String incidentType;

    /**
     * 设备名称
     */
    @TableField("deviceName")
    private String deviceName;

    /**
     * 抓拍图
     */
    @TableField("screenShot")
    private String screenShot;

    /**
     * 状态（0 未处理 1 已处理 3 误报警）
     */
    @TableField("status")
    private Integer status;

    /**
     * 联系人
     */
    @TableField("contact")
    private String contact;

    /**
     * 备注
     */
    @TableField("note")
    private String note;


}
