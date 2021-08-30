package com.jr.gitdemo.dao;

import com.jr.gitdemo.entity.TaskInfo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wujiangwei
 * @date 2019/5/20 16:16
 */
@Mapper
@Component
public interface QuartzJobMapper {
     List<TaskInfo> getJobInfo(TaskInfo taskInfo);
}
