package com.kristijanmihaljinac.taskmanagementspring.controller;

import com.kristijanmihaljinac.taskmanagementspring.domain.enums.StatusEnum;
import com.kristijanmihaljinac.taskmanagementspring.service.TaskService;
import com.kristijanmihaljinac.taskmanagementspring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String dashboard(Model model){
        model.addAttribute("todoTasks", taskService.findByStatusId(StatusEnum.TODO.getValue()));
        model.addAttribute("inProgressTasks", taskService.findByStatusId(StatusEnum.IN_PROGRESS.getValue()));
        model.addAttribute("doneTasks", taskService.findByStatusId(StatusEnum.DONE.getValue()));
        return "pages/task_dashboard";
    }

    @GetMapping("new")
    public String newTask(){
        return "pages/new_task";
    }
}
