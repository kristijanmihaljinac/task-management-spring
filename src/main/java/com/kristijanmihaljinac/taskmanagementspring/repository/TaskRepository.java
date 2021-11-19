package com.kristijanmihaljinac.taskmanagementspring.repository;

import com.kristijanmihaljinac.taskmanagementspring.domain.Task;

import java.util.Optional;
import java.util.Set;

public interface TaskRepository {
    Set<Task> findAll();
    Optional<Task> findById(Long id);
    Optional<Task> save(Task task);
    Set<Task> findByUserAssignedTo(String userAssignedToUsername);
    void deleteById(Long id);
    Set<Task> findByStatusId(Long statusId);
    Set<Task> findByUserAssignedToAndStatusId(String userAssignedToUsername, Long statusId);
}
