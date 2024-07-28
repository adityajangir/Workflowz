package com.example.Workflowz.controllers;
import com.example.Workflowz.models.WorkflowstepModel;
import com.example.Workflowz.services.WorkflowstepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workflow-steps")
public class WorkflowstepController {

    @Autowired
    private WorkflowstepService workflowStepService;

    @GetMapping
    public List<WorkflowstepModel> getAllWorkflowSteps() {
        return workflowStepService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<WorkflowstepModel> getWorkflowStepById(@PathVariable Long id) {
        return workflowStepService.findById(id);
    }

    @PostMapping
    public WorkflowstepModel createWorkflowStep(@RequestBody WorkflowstepModel workflowStep) {
        return workflowStepService.save(workflowStep);
    }

    @PutMapping("/{id}")
    public WorkflowstepModel updateWorkflowStep(@PathVariable Long id, @RequestBody WorkflowstepModel workflowStep) {
        workflowStep.setId(id);
        return workflowStepService.save(workflowStep);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkflowStep(@PathVariable Long id) {
        workflowStepService.deleteById(id);
    }
}
