package com.fuyunwang.chuoyue.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author FuyunWang
 * @since 2020-07-21
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class StudentVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String operator;

    private Integer status;

    private String studentId;

    private String studentImg;

    private String studentName;

    private String studentPhone;

    private BigDecimal studentPrice;

    private String studentSchool;

    private LocalDateTime updateTime;

}
