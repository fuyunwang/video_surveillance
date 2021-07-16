package com.fuyunwang.chuoyue.quartz;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fuyunwang.chuoyue.system.entity.TbAgent;
import com.fuyunwang.chuoyue.system.mapper.TbAgentMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: FuyunWang
 * @Date: 2021-07-16 9:50
 */
@Slf4j
public class SampleJob implements Job {
    @Autowired
    private TbAgentMapper tbAgentMapper;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        QueryWrapper<TbAgent> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("*");
//        tbAgentMapper.delete(queryWrapper);
        List<TbAgent> tbAgents = tbAgentMapper.selectList(queryWrapper);
        System.out.println("清表"+dateFormat.format(new Date()));
//        log.info(JSONUtil.toJsonPrettyStr(tbAgents));
    }
}
