package com.fuyunwang.chuoyue.system.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Date: 2020/10/11 19:03
 * @Author: FuyunWang
 * @Description:
 */
@Data
public class DeviceDto {
    @NotNull(message = "算法id不能为空")
    private Integer id;
    @NotNull(message = "状态不能为空")
    private Integer state;
}
