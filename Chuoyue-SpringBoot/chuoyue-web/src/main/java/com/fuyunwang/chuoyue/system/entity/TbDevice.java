package com.fuyunwang.chuoyue.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-10-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbDevice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 设备名称
     */
    @TableField("deviceName")
    private String deviceName;

    /**
     * 设备用户名
     */
    @TableField("deviceUserName")
    private String deviceUserName;

    /**
     * 设备IP地址
     */
    @TableField("deviceIp")
    private String deviceIp;

    /**
     * RTSP端口
     */
    @TableField("RTSP")
    private String rtsp;

    /**
     * 网关
     */
    @TableField("gateway")
    private String gateway;

    /**
     * 状态（0正常 1异常）
     */
    @TableField("status")
    private Integer status;

    /**
     * 组织
     */
    @TableField("departmentName")
    private String departmentName;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 状态
     */
    @TableField("state")
    private Integer state;


}
