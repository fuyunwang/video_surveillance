package com.fuyunwang.chuoyue.common.annotation;


import com.fuyunwang.chuoyue.common.handler.MobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @author: FuyunWang
 * @time: 2020/7/19 14:14
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobileValidator.class)
public @interface MobileCheck {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
