package com.example.Workflowz.controllers;


import com.example.Workflowz.models.ActionModel;
import com.example.Workflowz.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actions")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @GetMapping
    public List<ActionModel> getAllActions() {
        return actionService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ActionModel> getActionById(@PathVariable Long id) {
        return actionService.findById(id);
    }

    @PostMapping
    public ActionModel createAction(@RequestBody ActionModel action) {
        return actionService.save(action);
    }

    @PutMapping("/{id}")
    public ActionModel updateAction(@PathVariable Long id, @RequestBody ActionModel action) {
        action.setId(id);
        return actionService.save(action);
    }

    @DeleteMapping("/{id}")
    public void deleteAction(@PathVariable Long id) {
        actionService.deleteById(id);
    }
}
