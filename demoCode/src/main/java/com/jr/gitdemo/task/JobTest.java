package com.jr.gitdemo.task;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author wujiangwei
 * @date 2019/5/17 9:28
 */
@Slf4j
public class JobTest implements Job {


    public void run(){
        System.out.println(new Date());
        log.info("111222333");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        run();
    }
}
