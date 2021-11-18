package com.kristijanmihaljinac.taskmanagementspring.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Task {
    private Long id;
    private String subject;
    private String description;
    private User assignedBy;
    private User assignedTo;
    private Status status;
    private Priority priority;
    private LocalDateTime deadline;

    private Task() {}

    public static Task Create(
            Long id,
            String subject,
            String description,
            User assignedBy,
            User assignedTo,
            Status status,
            Priority priority,
            LocalDateTime deadline
    ){
        Task ts = new Task();
        ts.setId(id);



        return ts;
    }

    public static Task Create(){
        return new Task();
    }
}
