package com.kristijanmihaljinac.taskmanagementspring.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@JsonSerialize
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The subject has not been entered!")
    @Size(min = 2, max = 20, message = "The subject should be between 2 and 20 characters long!")
    private String subject;


    @NotEmpty(message = "The description has not been entered")
    @Size(min = 10, max = 100, message = "The description should be between 10 and 20 characters long!")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_assigned_by", nullable = false, referencedColumnName = "username")
    private User assignedBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_assigned_to", nullable = true, referencedColumnName = "username")
    private User assignedTo;

    @NotEmpty(message = "Task must be assigned to user")
    @NotNull(message = "Task must be assigned to user")
    @Column(name = "user_assigned_to", insertable = false, updatable = false)
    private String assignedToUsername;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Priority.class)
    @JoinColumn(name = "priority_id", nullable = false, referencedColumnName = "id")
    private Priority priority;

    @Column(name = "priority_id", insertable = false, updatable = false)
    private Long priorityId;



    @NotNull(message = "The deadline date has not been entered")
    @FutureOrPresent(message = "The deadline date can not be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime deadline;

    public Task() {}



    public static Task create(
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

        if(priority != null){
            ts.setPriorityId(ts.getPriority().getId());
        }

        if(assignedTo != null){
            ts.setAssignedToUsername(ts.assignedTo.getUsername());
        }

        return ts;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(User assignedBy) {
        this.assignedBy = assignedBy;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getAssignedToUsername() {
        return assignedToUsername;
    }

    public void setAssignedToUsername(String assignedToUsername) {
        this.assignedToUsername = assignedToUsername;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }


}
