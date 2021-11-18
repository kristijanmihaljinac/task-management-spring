package com.kristijanmihaljinac.taskmanagementspring.repository;

import com.kristijanmihaljinac.taskmanagementspring.domain.Task;

import java.util.Optional;
import java.util.Set;

public interface TaskRepository {
    Set<Task> findAll();
    Optional<Task> save(Task task);
    Set<Task> findByUserAssignedBy(Long userAssignedById);
    void deleteById(Long id);
}
