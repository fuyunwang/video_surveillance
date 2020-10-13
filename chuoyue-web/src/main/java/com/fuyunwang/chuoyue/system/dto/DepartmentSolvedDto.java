package com.fuyunwang.chuoyue.system.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Date: 2020/10/13 14:03
 * @Author: FuyunWang
 * @Description:
 */
@Data
public class DepartmentSolvedDto {
    private Integer departmentId;
    @NotNull(message = "联系人手机号不能为空")
    private String contact;
    @NotNull(message = "备注不能为空")
    private String note;
}
