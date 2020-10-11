package com.fuyunwang.chuoyue.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Date: 2020/10/11 19:04
 * @Author: FuyunWang
 * @Description:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceVo {
    private Integer id;

    private String deviceName;

    private Integer status;

    private Integer state;
}
