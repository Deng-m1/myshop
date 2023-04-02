package com.dbj.shopquartz.demo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {
    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job= JobBuilder.newJob(HelloJob.class)
                    .withIdentity("job1","group1").build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1","group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.
                            simpleSchedule().
                            withIntervalInSeconds(5).
                            repeatForever())
                    .build();
            scheduler.scheduleJob(job,trigger);

            Thread.sleep(30000);
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
