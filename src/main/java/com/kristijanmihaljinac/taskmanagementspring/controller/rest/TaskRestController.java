package com.kristijanmihaljinac.taskmanagementspring.controller.rest;

import com.kristijanmihaljinac.taskmanagementspring.domain.Task;
import com.kristijanmihaljinac.taskmanagementspring.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/task")
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService vaccinationService) {
        this.taskService = vaccinationService;
    }

    @GetMapping
    public Iterable<Task> findAll() {
        return taskService.findAll();
    }


    @GetMapping("{id}")
    public Task findOne(@PathVariable Long id) {
        return taskService.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task was not found")
                );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Task save(@Valid @RequestBody Task task) {

        if(task.getId() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task ID must be left empty when creating a task");
        }

        return taskService.saveJpa(task);
    }

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if(!taskService.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with the provided ID does not exist");
        }

        taskService.deleteById(id);
    }

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}/update-subject")
    public Task updateSubject(@PathVariable Long id, @RequestParam String subject) {
        final Task task = taskService.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with the provided ID does not exist")
                );

        task.setSubject(subject);

        return taskService.saveJpa(task);
    }


    // ConstraintViolationException is thrown in repository layer if an invalid entity is persisted by JPA
    // MethodArgumentNotValidException is thrown in controller layer if an invalid argument, annotated with @Valid, is passed
    @ExceptionHandler(value = {ConstraintViolationException.class, MethodArgumentNotValidException.class})
    ResponseEntity<String> handleConstraintViolationException(Exception e) {
        return new ResponseEntity<>("Validation error occured: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
