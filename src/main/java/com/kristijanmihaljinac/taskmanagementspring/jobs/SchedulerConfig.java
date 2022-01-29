package com.kristijanmihaljinac.taskmanagementspring.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail taskPrintJobDetail() {
        return JobBuilder
                .newJob(TaskPrintJob.class)
                .withIdentity("taskPrintTrigger")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger taskPrintTrigger() {
        // for cron use cases use CronScheduleBuilder instead of SimpleScheduleBuilder in the .withSchedule() method
        // cron expressions are explained here http://www.quartz-scheduler.org/documentation/quartz-2.3.0/tutorials/crontrigger.html
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 12 * * ?");

        SimpleScheduleBuilder scheduleBuilder =
                SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever();

        return TriggerBuilder.newTrigger().forJob(taskPrintJobDetail())
                .withIdentity("taskPrintTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
