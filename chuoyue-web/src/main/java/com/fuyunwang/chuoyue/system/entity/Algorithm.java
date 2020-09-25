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
public class Algorithm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 算法名称
     */
    @TableField("algorithmName")
    private String algorithmName;

    /**
     * 算法开始时间
     */
    @TableField("startTime")
    private LocalDateTime startTime;

    /**
     * 算法结束时间
     */
    @TableField("stopTime")
    private LocalDateTime stopTime;

    /**
     * 报警间隔(秒)
     */
    @TableField("alarmInterval")
    private Integer alarmInterval;

    /**
     * 设备名称
     */
    @TableField("deviceName")
    private String deviceName;


}
