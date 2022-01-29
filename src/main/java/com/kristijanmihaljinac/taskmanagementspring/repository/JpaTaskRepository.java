package com.kristijanmihaljinac.taskmanagementspring.repository;

import com.kristijanmihaljinac.taskmanagementspring.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface JpaTaskRepository extends CrudRepository<Task,Long> {
    Set<Task> findAll();
    Optional<Task> findById(Long id);

    Set<Task> findAllByAssignedToUsername(String assignedToUsername);


    Set<Task> findAllBySubjectContainsAndAssignedToUsername(String subject, String assignedToUsername);
    Set<Task> findAllBySubjectContainsAndPriorityIdAndAssignedToUsername(String subject, Long priorityId, String assignedToUsername);
    Set<Task> findAllBySubjectContainsAndPriorityIdAndAssignedToUsernameAndStatusId(String subject, Long priorityId, String assignedToUsername, Long statusId);

    Set<Task> findAllBySubjectContains(String subject);


    Set<Task> findAllByPriorityIdAndStatusId(Long priorityId, Long statusId);
    Set<Task> findAllBySubjectContainsAndStatusId(String subject, Long statusId);
    Set<Task> findAllBySubjectContainsAndPriorityIdAndStatusId(String subject, Long priorityId, Long statusId);


    Set<Task> findAllByPriorityIdAndStatusIdAndAssignedToUsername(Long priorityId, Long statusId, String username);
    Set<Task> findAllBySubjectContainsAndStatusIdAndAssignedToUsername(String subject, Long statusId,  String username);
    Set<Task> findAllBySubjectContainsAndPriorityIdAndStatusIdAndAssignedToUsername(String subject, Long priorityId, Long statusId, String username);

    Set<Task> findAllByStatusId(Long id);
    Set<Task> findAllByStatusIdAndAssignedToUsername(Long id, String assignedToUsername);

}
