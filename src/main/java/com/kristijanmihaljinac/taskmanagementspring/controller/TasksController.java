package com.kristijanmihaljinac.taskmanagementspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TasksController {

    @GetMapping("/home")
    public String loginSubmit(){
        return "/pages/landing_page";
    }
}
