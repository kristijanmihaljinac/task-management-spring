package com.kristijanmihaljinac.taskmanagementspring.jobs;

import com.kristijanmihaljinac.taskmanagementspring.domain.Task;
import com.kristijanmihaljinac.taskmanagementspring.repository.JpaTaskRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TaskPrintJob extends QuartzJobBean {

    private final Logger log = LoggerFactory.getLogger(TaskPrintJob.class);

    private final JpaTaskRepository jpaTaskRepository;

    public TaskPrintJob(JpaTaskRepository jpaTaskRepository) {
        this.jpaTaskRepository = jpaTaskRepository;
    }


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Iterable<Task> tasks = jpaTaskRepository.findAll();

        if(tasks.iterator().hasNext()) {
            log.info("These are the tasks currently entered in the app");
            tasks.forEach(it ->
                    log.info(it.getSubject())
            );
        } else {
            log.info("These are currently no tasks in the app");
        }
    }
}
