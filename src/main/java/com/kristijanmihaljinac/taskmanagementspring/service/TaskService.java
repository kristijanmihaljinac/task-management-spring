package com.kristijanmihaljinac.taskmanagementspring.service;

import com.kristijanmihaljinac.taskmanagementspring.domain.Status;
import com.kristijanmihaljinac.taskmanagementspring.domain.Task;
import com.kristijanmihaljinac.taskmanagementspring.domain.User;
import com.kristijanmihaljinac.taskmanagementspring.domain.enums.StatusEnum;
import com.kristijanmihaljinac.taskmanagementspring.repository.TaskRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Set<Task> findAll() {
        return taskRepository
                .findAll()
                .stream()
                .collect(Collectors.toUnmodifiableSet());
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public Optional<Task> save(Task task) {

        return taskRepository.save(task);
    }

    public Set<Task> findByUserAssignedTo(String userAssignedToUsername){
        return taskRepository.findByUserAssignedTo(userAssignedToUsername);
    }

    public void deleteById(Long id){
         taskRepository.deleteById(id);
    }

    public Set<Task> findByStatusId(Long statusId){
        return taskRepository.findByStatusId(statusId);
    }

    public Set<Task> findByUserAssignedToAndStatusId(String userAssignedToUsername, Long statusId){
        return taskRepository.findByUserAssignedToAndStatusId(userAssignedToUsername, statusId);
    }

    public void changeStatus(Long id, Long statusId){
        taskRepository.changeStatus(id, statusId);
    }

    

}
