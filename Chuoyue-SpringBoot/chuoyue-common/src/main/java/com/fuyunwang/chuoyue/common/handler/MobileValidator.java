package com.fuyunwang.chuoyue.common.handler;

import com.fuyunwang.chuoyue.common.annotation.MobileCheck;
import com.fuyunwang.chuoyue.common.base.GlobalConstant;
import com.fuyunwang.chuoyue.common.utils.RegexUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 手机号校验
 * @author: FuyunWang
 * @time: 2020/7/19 14:16
 */
public class MobileValidator implements ConstraintValidator<MobileCheck, String> {

    @Override
    public void initialize(MobileCheck constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = GlobalConstant.MOBILE_REG;
                return RegexUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
