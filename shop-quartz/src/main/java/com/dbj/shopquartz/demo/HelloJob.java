package com.dbj.shopquartz.demo;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;

public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Object result = jobExecutionContext.getResult();
        JobDetail jobDetail = jobExecutionContext.getJobDetail();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Hello Job!!! 时间：" + sdf.format(jobExecutionContext.getFireTime()));
        System.out.println(result);
        System.out.println(jobDetail.getDescription());
    }
}
