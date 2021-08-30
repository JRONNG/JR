package com.jr.gitdemo.service;



import com.jr.gitdemo.dao.QuartzJobMapper;
import com.jr.gitdemo.entity.TaskInfo;
import com.jr.gitdemo.task.JobTest;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wujiangwei
 * @date 2019/5/15 10:45
 */
@Service
@Slf4j
public class TaskService {
    @Autowired
    private Scheduler scheduler;
    final static String taskClassPath = "com.jr.gitdemo.task";

    @Autowired
    QuartzJobMapper quartzJobMapper;
    /**
     * 任务列表.
     */
    public List<TaskInfo> list(TaskInfo taskInfo) {

        TriggerKey triggerKey ;
        JobKey jobKey;
        List<TaskInfo> list = new ArrayList<>();
        try {
            list = quartzJobMapper.getJobInfo(taskInfo);
            if(list.size()>0){
                for(int i=0;i<list.size();i++){
                    triggerKey = TriggerKey.triggerKey(list.get(i).getJobName(), list.get(i).getJobGroup());
                    jobKey = JobKey.jobKey(list.get(i).getJobName(), list.get(i).getJobGroup());
                    list.get(i).setJobStatus(scheduler.getTriggerState(triggerKey).toString());
                    list.get(i).setJobDataMap(scheduler.getJobDetail(jobKey).getJobDataMap());
                }
            }

//            for (final String groupJob : scheduler.getJobGroupNames()) {
//                for (final JobKey jobKey : scheduler.getJobKeys(GroupMatcher.groupEquals(groupJob))) {
//
//                    final List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
//                    for (final Trigger trigger : triggers) {
//                        final Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
//                        final JobDetail jobDetail = scheduler.getJobDetail(jobKey);
//
//                        String cronExpression = "";
//                        String createTime = "";
//
//                        if (trigger instanceof CronTrigger) {
//                            final CronTrigger cronTrigger = (CronTrigger) trigger;
//                            cronExpression = cronTrigger.getCronExpression();
//                            createTime = cronTrigger.getDescription();
//                        }
//
//                        final TaskInfo info = new TaskInfo();
//                        info.setJobName(jobKey.getName());
//                        info.setJobGroup(jobKey.getGroup());
//                        info.setJobDescription(jobDetail.getDescription());
//                        info.setJobStatus(triggerState.name());
//                        info.setCronExpression(cronExpression);
//                        info.setCreateTime(createTime);
//                        info.setJobDataMap(jobDetail.getJobDataMap());
//                        list.add(info);
//                    }
//                }
//            }
        } catch (SchedulerException e) {
            log.error("获取任务列表异常", e);
        }

        return list;
    }

    /**
     * 保存定时任务.
     */
//    final TaskInfo info
    public void addJob() throws Exception {
//        final String jobName = info.jobClass();
//        final String jobGroup = info.getJobGroup();
//        final String cronExpression = info.getCronExpression();
//        final String jobDescription = info.getJobDescription();
        String jobName ="test";
        String jobGroup = "test";
        String cronExpression = "1 * * * * ? *";
        String jobDescription ="测试";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String createTime = df.format(new Date());

        try {

            /*if (checkExists(jobName, jobGroup)) {
                log.info("===> AddJob fail, job already exist, jobGroup:{}, jobName:{}", jobGroup, jobName);
                throw new ServiceException(String.format("Job已经存在, jobName:{%s},jobGroup:{%s}", jobName, jobGroup));
            }*/

            final TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            final CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
                    .withMisfireHandlingInstructionDoNothing();
            final CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                    .withDescription(createTime).withSchedule(cronScheduleBuilder).build();

//            final Class<? extends Job> clazz = Class.forName(taskClassPath+"."+jobName).asSubclass(Job.class);

            final JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            final JobDetail jobDetail = JobBuilder.newJob(JobTest.class).withIdentity(jobKey)
                    .withDescription(jobDescription)
//            info.getJobDataMap()
//                    .usingJobData(null)
                    .build();

            scheduler.scheduleJob(jobDetail, cronTrigger);
//        } catch (SchedulerException | ClassNotFoundException e) {

        } catch (SchedulerException  e) {
            System.out.println(e);
            throw new Exception("类名不存在或执行表达式错误");
        }
    }

    /**
     * 修改定时任务.
     */
    public void edit(final TaskInfo info) throws Exception {
        final String jobName = info.jobClass();
        final String jobGroup = info.getJobGroup();
        final String cronExpression = info.getCronExpression();
        final String jobDescription = info.getJobDescription();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String createTime = df.format(new Date());
        try {
            if (!checkExists(jobName, jobGroup)) {
                log.info("===> EditJob fail, job already exist, jobGroup:{}, jobName:{}", jobGroup, jobName);
                throw new Exception(String.format("Job不存在, jobName:{%s},jobGroup:{%s}", jobName, jobGroup));
            }

            final TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            final CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
                    .withMisfireHandlingInstructionDoNothing();
            final CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                    .withDescription(createTime).withSchedule(cronScheduleBuilder).build();

            final JobKey jobKey = new JobKey(jobName, jobGroup);
            final JobBuilder jobBuilder = scheduler.getJobDetail(jobKey).getJobBuilder();

            final JobDetail jobDetail = jobBuilder.setJobData(info.getJobDataMap()).withDescription(jobDescription).build();

            final Set<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(cronTrigger);

            scheduler.scheduleJob(jobDetail, triggerSet, true);
        } catch (SchedulerException e) {
            throw new Exception("类名不存在或执行表达式错误");
        }
    }

    /**
     * 删除定时任务.
     */
    public void delete(final String jobName, final String jobGroup) throws Exception {
        final TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (checkExists(jobName, jobGroup)) {
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
                log.info("===> delete, triggerKey:{}", triggerKey);
            }
        } catch (SchedulerException e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 暂停定时任务.
     */
    public void pause(final String jobName, final String jobGroup) throws Exception {
        final TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (checkExists(jobName, jobGroup)) {
                scheduler.pauseTrigger(triggerKey);
                log.info("===> Pause success, triggerKey:{}", triggerKey);
            }
        } catch (SchedulerException e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 重新开始任务.
     */
    public void resume(final String jobName, final String jobGroup) throws Exception {
        final TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (checkExists(jobName, jobGroup)) {
                scheduler.resumeTrigger(triggerKey);
                log.info("===> Resume success, triggerKey:{}", triggerKey);
            }
        } catch (SchedulerException e) {
            log.error("恢复任务时出现异常", e);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 验证是否存在.
     */
    private boolean checkExists(String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return scheduler.checkExists(triggerKey);
    }
}
