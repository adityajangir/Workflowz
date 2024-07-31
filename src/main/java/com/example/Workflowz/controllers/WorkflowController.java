package com.example.Workflowz.controllers;
import com.example.Workflowz.models.*;
import com.example.Workflowz.repository.WorkflowRepository;
import com.example.Workflowz.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/workflows")
public class WorkflowController {

//    @Autowired
//    WorkflowModel workflowModel;
    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private WorkflowstepService workflowstepService;

    @Autowired
    private ActionService actionService;

    @Autowired
    private ConditionService conditionService;

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public List<WorkflowModel> getAllWorkflows() {
        return workflowService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<WorkflowModel> getWorkflowById(@PathVariable Long id) {
        return workflowService.findById(id);
    }

    @GetMapping("/")
    public  List<WorkflowModel> getWorkflows() {
        return workflowService.findAll();
    }


    @PostMapping
    public WorkflowModel createWorkflow(@RequestBody WorkflowModel workflow) {
        WorkflowModel workflowModel = new WorkflowModel();
        workflowModel.setName(workflow.getName());

        List<WorkflowstepModel> steps = new ArrayList<>();
        for (WorkflowstepModel step : workflow.getSteps()) {
            WorkflowstepModel stepModel = new WorkflowstepModel();
            stepModel.setType(step.getType());

            if (step.getAction() != null) {
                ActionModel actionModel = actionService.save(step.getAction());
                stepModel.setAction(actionModel);
            }
            if (step.getCondition() != null) {
                ConditionModel conditionModel = conditionService.save(step.getCondition());
                stepModel.setCondition(conditionModel);
            }
            if (step.getActivity() != null) {
                ActivityModel activityModel = activityService.save(step.getActivity());
                stepModel.setActivity(activityModel);
            }

            stepModel.setWorkflow(workflowModel);
            steps.add(stepModel);
        }

        workflowModel.setSteps(steps);
        return workflowService.save(workflowModel);
    }


    @PutMapping("/{id}")
    public WorkflowModel updateWorkflow(@PathVariable Long id, @RequestBody WorkflowModel workflow) {
        workflow.setId(id);
        return workflowService.save(workflow);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkflow(@PathVariable Long id) {
        System.out.println(id);
        workflowService.deleteById(id);
    }
}
