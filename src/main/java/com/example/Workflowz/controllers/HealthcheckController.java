package com.example.Workflowz.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthcheckController {
    @GetMapping("/")
    public String checkhealth() {
        return "Get api working";
    }
}
