package com.kristijanmihaljinac.taskmanagementspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("task")
public class TasksController {

    @GetMapping("dashboard")
    public String dashboard(){
        return "pages/task_dashboard";
    }

    @GetMapping("new")
    public String newTask(){
        return "pages/new_task";
    }
}
