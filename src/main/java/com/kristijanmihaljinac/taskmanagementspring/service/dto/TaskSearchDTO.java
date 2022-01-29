package com.kristijanmihaljinac.taskmanagementspring.service.dto;

public class TaskSearchDTO {

    private String taskSubject;

    private Long priorityId;


    public String getTaskSubject() {
        return taskSubject;
    }

    public void setTaskSubject(String taskSubject) {
        this.taskSubject = taskSubject;
    }

    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
    }
}
