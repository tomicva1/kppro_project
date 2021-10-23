package com.application.kppro_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    // add request mapping for /leaders

    @GetMapping("/admin")
    public String showAdmins() {

        return "admin";
    }

    // add request mapping for /systems

    @GetMapping("/manager")
    public String showManagers() {

        return "manager";
    }
}
