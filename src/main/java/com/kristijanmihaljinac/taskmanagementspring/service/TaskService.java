package com.kristijanmihaljinac.taskmanagementspring.service;

import com.kristijanmihaljinac.taskmanagementspring.domain.Status;
import com.kristijanmihaljinac.taskmanagementspring.domain.Task;
import com.kristijanmihaljinac.taskmanagementspring.domain.User;
import com.kristijanmihaljinac.taskmanagementspring.domain.enums.StatusEnum;
import com.kristijanmihaljinac.taskmanagementspring.repository.JpaTaskRepository;
import com.kristijanmihaljinac.taskmanagementspring.repository.TaskRepository;
import com.kristijanmihaljinac.taskmanagementspring.service.dto.TaskSearchDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final JpaTaskRepository jpaTaskRepository;

    public TaskService(TaskRepository taskRepository, JpaTaskRepository jpaTaskRepository) {
        this.taskRepository = taskRepository;
        this.jpaTaskRepository = jpaTaskRepository;
    }

    public Set<Task> findAll() {
        return jpaTaskRepository.findAll();
    }

    public Optional<Task> findById(Long id) {
        return jpaTaskRepository.findById(id);
    }

    public Optional<Task> save(Task task) {

        return taskRepository.save(task);
    }

    public Set<Task> findByUserAssignedTo(String userAssignedToUsername){
        return jpaTaskRepository.findAllByAssignedToUsername(userAssignedToUsername);
    }

    public Set<Task> findAllBySubjectAndAssignedToUsername(String taskSubject, String assignedTo){
        return jpaTaskRepository.findAllBySubjectContainsAndAssignedToUsername(taskSubject, assignedTo);
    }

    public Set<Task> findAllBySubject(String taskSubject){
        return jpaTaskRepository.findAllBySubjectContains(taskSubject);
    }

    public Set<Task> findAllBySubjectAndPriorityAndAssignedToUsername(String taskSubject, Long priorityId, String assignedTo){
        return jpaTaskRepository.findAllBySubjectContainsAndPriorityIdAndAssignedToUsername(taskSubject, priorityId, assignedTo);
    }



    public void deleteById(Long id){
         jpaTaskRepository.deleteById(id);
    }

    public Set<Task> findByStatusId(Long statusId){
        return jpaTaskRepository.findAllByStatusId(statusId);
    }

    public Task saveJpa(Task task) {
        return jpaTaskRepository.save(task);
    }

    public Set<Task> findByUserAssignedToAndStatusId(String userAssignedToUsername, Long statusId){
        return jpaTaskRepository.findAllByStatusIdAndAssignedToUsername(statusId, userAssignedToUsername);
    }

    public boolean existsById(Long id) {
        return jpaTaskRepository.existsById(id);
    }

    public void changeStatus(Long id, Long statusId){
        taskRepository.changeStatus(id, statusId);
    }



    public Set<Task> findByStatusIdAndSearchDto(Long statusId, TaskSearchDTO searchDTO){
        boolean isSubjectFilled = searchDTO != null && searchDTO.getTaskSubject() != null && !searchDTO.getTaskSubject().isBlank();
        boolean isPriorityIdFilled =searchDTO != null && searchDTO.getPriorityId() != null && searchDTO.getPriorityId() > 0;

        if(isSubjectFilled && isPriorityIdFilled){
           return jpaTaskRepository.findAllBySubjectContainsAndPriorityIdAndStatusId(searchDTO.getTaskSubject(), searchDTO.getPriorityId(), statusId);
        }

        if (isSubjectFilled){
            return  jpaTaskRepository.findAllBySubjectContainsAndStatusId(searchDTO.getTaskSubject(), statusId);
        }

        if (isPriorityIdFilled){
            return  jpaTaskRepository.findAllByPriorityIdAndStatusId(searchDTO.getPriorityId(), statusId);
        }


        return jpaTaskRepository.findAllByStatusId(statusId);
    }

    public Set<Task> findByUserAssignedToAndStatusIdAndSearchDto(String userAssignedToUsername, Long statusId, TaskSearchDTO searchDTO){

        boolean isSubjectFilled = searchDTO != null && searchDTO.getTaskSubject() != null && !searchDTO.getTaskSubject().isBlank();
        boolean isPriorityIdFilled =searchDTO != null && searchDTO.getPriorityId() != null && searchDTO.getPriorityId() > 0;

        if(isSubjectFilled && isPriorityIdFilled){
            return jpaTaskRepository.findAllBySubjectContainsAndPriorityIdAndStatusIdAndAssignedToUsername(searchDTO.getTaskSubject(), searchDTO.getPriorityId(), statusId, userAssignedToUsername);
        }

        if (isSubjectFilled){
            return  jpaTaskRepository.findAllBySubjectContainsAndStatusIdAndAssignedToUsername(searchDTO.getTaskSubject(), statusId, userAssignedToUsername);
        }

        if (isPriorityIdFilled){
            return  jpaTaskRepository.findAllByPriorityIdAndStatusIdAndAssignedToUsername(searchDTO.getPriorityId(), statusId, userAssignedToUsername);
        }

        return jpaTaskRepository.findAllByStatusIdAndAssignedToUsername(statusId, userAssignedToUsername);
    }

}
