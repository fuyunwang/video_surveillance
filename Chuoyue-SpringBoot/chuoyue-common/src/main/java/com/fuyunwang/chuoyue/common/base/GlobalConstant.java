package com.fuyunwang.chuoyue.common.base;

/**
 * @description:
 * @author: FuyunWang
 * @time: 2020/7/19 14:17
 */
public class GlobalConstant {
    public static final String TOAST_MESSAGE="message";
    // 简单手机号正则（这里只是简单校验是否为 11位，实际规则更复杂）
    public static final String MOBILE_REG = "[1]\\d{10}";
    public interface RabbitMQ{
        String QUEUE_NAME_CHUOYUE = "chuoyue";
        String EXCHANGE_NAME_TOPIC="topicExchange";
    }

    public interface Algorithm{
        String SELECTED_ALGORITHM="selected_algorithm";
        String ALGORITHM_PREFIX="algorithm:";
    }

    public interface File{
        String UPLOAD_EMPTY_ERROR="上传文件不能为空";
        String UPLOAD_FAILURE="文件上传失败";
    }
}
