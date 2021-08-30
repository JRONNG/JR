package com.jr.gitdemo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.quartz.JobDataMap;

/**
 * @author wujiangwei
 * @date 2019/5/15 10:46
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskInfo {
    private JobDataMap jobDataMap;
    private String belong = "";//jobDataMap.partner 对应归属  SX-山西  LHK-老河口
    // 任务名称

    private String jobName;

    //任务分组

    private String jobGroup;

    //任务描述

    private String jobDescription;

    //任务状态

    private String jobStatus;

    //任务表达式

    private String cronExpression;


    private String createTime;

    public String jobClass() {
        return jobName == null ? null : jobName.split("#")[0];
    }
}
