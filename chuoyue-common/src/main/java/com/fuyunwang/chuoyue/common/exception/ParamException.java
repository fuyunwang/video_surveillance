package com.fuyunwang.chuoyue.common.exception;

import lombok.Data;

/**
 * @Date: 2020/10/11 19:28
 * @Author: FuyunWang
 * @Description:
 */
@Data
public class ParamException extends RuntimeException{
    private String errorMesssage;

    public ParamException( String errorMesssage) {
        super(errorMesssage);
        this.errorMesssage = errorMesssage;
    }
}
