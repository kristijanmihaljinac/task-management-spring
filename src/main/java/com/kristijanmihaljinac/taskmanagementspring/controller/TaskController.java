package com.kristijanmihaljinac.taskmanagementspring.controller;

import com.kristijanmihaljinac.taskmanagementspring.domain.Status;
import com.kristijanmihaljinac.taskmanagementspring.domain.Task;
import com.kristijanmihaljinac.taskmanagementspring.domain.User;
import com.kristijanmihaljinac.taskmanagementspring.domain.enums.PriorityEnum;
import com.kristijanmihaljinac.taskmanagementspring.domain.enums.StatusEnum;
import com.kristijanmihaljinac.taskmanagementspring.service.TaskService;
import com.kristijanmihaljinac.taskmanagementspring.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("task")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("dashboard")
    public String dashboard(Model model, Authentication authentication){
        addTasksToModel(model, authentication);
        return "pages/task_dashboard";
    }

    @GetMapping("new")
    @RolesAllowed("ROLE_ADMIN")
    public String newTask(Task task, Model model, Authentication authentication){

        model.addAttribute("pageTitle", "New task");
        model.addAttribute("taskPriorities", PriorityEnum.values());
        model.addAttribute("users", userService.findAll());
        return "pages/new_task";
    }

    @GetMapping("edit/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public String newTask(@PathVariable Long id, Model model, Authentication authentication){

        Optional<Task> taskToEdit = taskService.findById(id);

        if(taskToEdit.isPresent()){
            Task task = taskToEdit.orElseThrow(RuntimeException::new);
            model.addAttribute("task", task);
        }
        model.addAttribute("pageTitle", "Edit task");
        model.addAttribute("taskPriorities", PriorityEnum.values());
        model.addAttribute("users", userService.findAll());
        return "pages/new_task";
    }

    @PostMapping("confirm-save")
    @RolesAllowed("ROLE_ADMIN")
    public String taskSaveConfirm(@Valid Task task, Errors errors, Model model, Authentication authentication) {
        if(errors.hasErrors()){
            model.addAttribute("taskPriorities", PriorityEnum.values());
            model.addAttribute("users", userService.findAll());
            return "pages/new_task";
        }

        task.setStatus(Status.create(StatusEnum.TODO.getValue()));
        task.setAssignedBy(User.create(authentication.getName()));
        taskService.save(task);
        addTasksToModel(model, authentication);

        return "pages/task_dashboard";
    }


    @RequestMapping("change-status-todo/{id}")
    public String changeStatusTodo(@PathVariable Long id, Model model, Authentication authentication){
        taskService.changeStatus(id, StatusEnum.TODO.getValue());
        addTasksToModel(model, authentication);
        return "pages/task_dashboard";
    }

    @RequestMapping("change-status-inprogress/{id}")
    public String changeStatusInProgress(@PathVariable Long id, Model model, Authentication authentication){
        taskService.changeStatus(id, StatusEnum.IN_PROGRESS.getValue());
        addTasksToModel(model, authentication);
        return "pages/task_dashboard";
    }

    @RequestMapping("change-status-done/{id}")
    public String changeStatusDone(@PathVariable Long id, Model model, Authentication authentication){

        taskService.changeStatus(id, StatusEnum.DONE.getValue());
        addTasksToModel(model, authentication);
        return "pages/task_dashboard";
    }

    @RequestMapping("delete/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public String deleteTask(@PathVariable Long id, Model model, Authentication authentication){

        taskService.deleteById(id);
        addTasksToModel(model, authentication);
        return "pages/task_dashboard";
    }

    private void addTasksToModel(Model model, Authentication authentication){

        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());

        if(roles.contains("ROLE_ADMIN")){
            model.addAttribute("todoTasks", taskService.findByStatusId(StatusEnum.TODO.getValue()));
            model.addAttribute("inProgressTasks", taskService.findByStatusId(StatusEnum.IN_PROGRESS.getValue()));
            model.addAttribute("doneTasks", taskService.findByStatusId(StatusEnum.DONE.getValue()));
        }
        else
        {
            model.addAttribute("todoTasks", taskService.findByUserAssignedToAndStatusId(authentication.getName(),StatusEnum.TODO.getValue()));
            model.addAttribute("inProgressTasks", taskService.findByUserAssignedToAndStatusId(authentication.getName(),StatusEnum.IN_PROGRESS.getValue()));
            model.addAttribute("doneTasks", taskService.findByUserAssignedToAndStatusId(authentication.getName(), StatusEnum.DONE.getValue()));
        }


    }
}
