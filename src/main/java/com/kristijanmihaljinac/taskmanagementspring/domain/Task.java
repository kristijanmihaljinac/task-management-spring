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
            User assignedTo,
            User assignedBy,
            Status status,
            Priority priority,
            LocalDateTime deadline
    ){

        Task ts = new Task();
        ts.setId(id);
        ts.setSubject(subject);
        ts.setDescription(description);
        ts.setAssignedTo(assignedTo);
        ts.setAssignedBy(assignedBy);
        ts.setStatus(status);
        ts.setPriority(priority);
        ts.setDeadline(deadline);


        return ts;
    }


}
