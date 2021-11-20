package com.kristijanmihaljinac.taskmanagementspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForbiddenController {

    @RequestMapping("/403")
    public String accessDenied() {
        return "pages/forbidden";
    }

}
