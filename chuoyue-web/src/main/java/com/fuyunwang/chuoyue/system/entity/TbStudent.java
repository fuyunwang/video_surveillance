package com.fuyunwang.chuoyue.system.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author FuyunWang
 * @since 2020-07-21
 */
@ApiModel(value="学生数据模型",description="学生数据模型")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("operator")
    @Excel(name = "操作人", width = 15)
    private String operator;

    @TableField("status")
    @Excel(name = "状态", width = 3)
    private Integer status;

    @TableField("student_id")
    @Excel(name = "学号", width = 15)
    private String studentId;

    @TableField("student_img")
    @Excel(name = "头像地址", width = 30)
    private String studentImg;

    @ApiModelProperty(value="学生姓名",name="studentName",example="FuyunWang")
    @TableField("student_name")
    @Excel(name = "姓名", width = 15)
    private String studentName;

    @TableField("student_phone")
    @Excel(name = "手机号", width = 15)
    private String studentPhone;

    @TableField("student_price")
    @Excel(name = "价格", width = 15)
    private BigDecimal studentPrice;

    @TableField("student_school")
    @Excel(name = "学生学校", width = 15)
    private String studentSchool;

    @Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")//使用poi工具的时候,注意使用Date而不是LocalDatetime
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;
}
