package com.example.Workflowz.services;

import com.example.Workflowz.models.*;
import com.example.Workflowz.repository.ExecutionRepository;
import com.example.Workflowz.repository.WorkflowresultRepository;
import com.example.Workflowz.repository.WorkflowstepresultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Slf4j
@Service
//public class ExecutionService {
//    @Autowired
//    private WorkflowService workflowService;
//
//    @Autowired
//    private ExecutionRepository executionRepository;
//
//    @Autowired
//    private WorkflowstepService workflowstepService;
//
//    public void startWorkflow(Long workflowId, Map<String, Object> inputData) {
//        Optional<WorkflowModel> workflowOpt = workflowService.findById(workflowId);
//        if (workflowOpt.isPresent()) {
//            WorkflowModel workflow = workflowOpt.get();
//            WorkflowstepModel initialStep = determineInitialStep(workflow, inputData);
//            executeStep(workflow, initialStep, inputData);
//        }
//    }
//
//    private WorkflowstepModel determineInitialStep(WorkflowModel workflow, Map<String, Object> inputData) {
////        return workflow.getSteps().stream()
////                .findFirst()
////                .orElse(null);
//        return workflow.getSteps().get(0);
//    }
//
//    private void executeStep(WorkflowModel workflow, WorkflowstepModel step, Map<String, Object> inputData) {
//        if (step == null) return;
////        log.info("This is the step"+step.toString());
//        String outcome = evaluateConditionOnActivity(step, inputData);
//
//        logExecution(workflow, step, outcome);
//
//        WorkflowstepModel nextStep = determineNextStep(step, outcome);
//        if (nextStep != null) {
//            executeStep(workflow, nextStep, inputData);
//        }
//    }
//
//    private String evaluateConditionOnActivity(WorkflowstepModel step, Map<String, Object> inputData) {
//        if (step.getCondition() != null && step.getAction() != null) {
//            ActionModel AM = step.getAction();
//            boolean conditionResult = true;
//            if(Objects.equals(AM.getName(), "DobCheck")){
//                conditionResult = DobCheck(step.getCondition(), inputData);
//            }else if(Objects.equals(AM.getName(), "PincodeCheck")){
//                conditionResult = PincodeCheck(step.getCondition(), inputData);
//            }else if(Objects.equals(AM.getName(), "GenderCheck")){
//                conditionResult = GenderCheck(step.getCondition(), inputData);
//            }else{
//                conditionResult = false;
//            }
//            return conditionResult ? "CONDITION_TRUE" : "CONDITION_FALSE";
//        }
//        return "NO_CONDITION";
//    }
//
//
//
//    private boolean GenderCheck(ConditionModel condition, Map<String, Object> inputData) {
//        return condition.getConditionText().equalsIgnoreCase((String) inputData.get("gender"));
//    }
//
//    private boolean PincodeCheck(ConditionModel condition, Map<String, Object> inputData) {
//        String prefix = condition.getConditionText().replaceAll("[^0-9]", "");
//        String pincode = inputData.get("pincode").toString();
//        return pincode.startsWith(prefix);
//    }
//
//    private boolean DobCheck(ConditionModel condition, Map<String, Object> inputData) {
//        String conditionText = condition.getConditionText();
//        int age = (int) inputData.get("age");
//        if (conditionText.contains("<")) {
//            String[] parts = conditionText.split("<");
//            return age < Integer.parseInt(parts[1]);
//        } else if (conditionText.contains(">")) {
//            String[] parts = conditionText.split(">");
//            return age > Integer.parseInt(parts[1]);
//        } else if (conditionText.contains("=")) {
//            String[] parts = conditionText.split("=");
//            return age == Integer.parseInt(parts[1]);
//        } else {
//            throw new IllegalArgumentException("Invalid condition: " + conditionText);
//        }
//    }
//
//
//    private WorkflowstepModel determineNextStep(WorkflowstepModel step, String outcome) {
//        List<WorkflowstepModel> steps = step.getWorkflow().getSteps();
//        int currentIndex = steps.indexOf(step);
//
//        if ("CONDITION_TRUE".equals(outcome)) {
//            return currentIndex + 1 < steps.size() ? steps.get(currentIndex + 1) : null;
//        } else if ("CONDITION_FALSE".equals(outcome)) {
//            return currentIndex + 2 < steps.size() ? steps.get(currentIndex + 2) : null;
//        } else if ("NO_CONDITION".equals(outcome)) {
//            return currentIndex + 1 < steps.size() ? steps.get(currentIndex + 1) : null;
//        }
//        return null;
//    }
//
//    private void logExecution(WorkflowModel workflow, WorkflowstepModel step, String outcome) {
//        ExecutionModel log = new ExecutionModel();
//        log.setWorkflow(workflow);
//        log.setStep(step);
//        log.setExecutionTime(LocalDateTime.now());
//        log.setOutcome(outcome);
//        log.setDetails("Executed step with outcome: " + outcome);
//        executionRepository.save(log);
//    }
//}










public class ExecutionService {

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private WorkflowresultRepository workflowresultRepository;

    @Autowired
    private WorkflowstepresultRepository workflowstepresultRepository;

    public void startWorkflow(Long workflowId, Map<String, Object> inputData) {
        Optional<WorkflowModel> workflowOpt = workflowService.findById(workflowId);
        if (workflowOpt.isPresent()) {
            WorkflowModel workflow = workflowOpt.get();
            WorkflowresultModel workflowResult = new WorkflowresultModel();
            workflowResult.setWorkflow(workflow);
            workflowResult = workflowresultRepository.save(workflowResult);
            WorkflowstepModel initialStep = determineInitialStep(workflow, inputData);
            executeStep(workflowResult, workflow, initialStep, inputData);
        }
    }

    private WorkflowstepModel determineInitialStep(WorkflowModel workflow, Map<String, Object> inputData) {
        return workflow.getSteps().get(0);
    }

    private void executeStep(WorkflowresultModel workflowResult, WorkflowModel workflow, WorkflowstepModel step, Map<String, Object> inputData) {
        if (step == null) return;
        Integer conditionResult = evaluateConditionOnActivity(step, inputData);

        logExecution(workflowResult, step, conditionResult, inputData);

        WorkflowstepModel nextStep = determineNextStep(step, conditionResult);
        if (nextStep != null) {
            executeStep(workflowResult, workflow, nextStep, inputData);
        }
    }

    private Integer evaluateConditionOnActivity(WorkflowstepModel step, Map<String, Object> inputData) {
        if (step.getCondition() != null && step.getAction() != null) {
            ActionModel action = step.getAction();
            Integer conditionResult = 0;
            switch (action.getName()) {
                case "DobCheck":
                    conditionResult = (DobCheck(step.getCondition(), inputData)? 1: 0);
                    break;
                case "PincodeCheck":
                    conditionResult = (PincodeCheck(step.getCondition(), inputData)? 1: 0);
                    break;
                case "GenderCheck":
                    conditionResult = (GenderCheck(step.getCondition(), inputData)? 1: 0);
                    break;
                default:
                    conditionResult = 0;
            }
            if(conditionResult==1 && Objects.equals(step.getActivity().getName(), "LoanStatus")){
                if(Objects.equals(action.getName(), "PincodeCheck")) conditionResult = 3;
                else conditionResult = 2;
            }
            return conditionResult;
        }
        return 0;
    }

    private boolean GenderCheck(ConditionModel condition, Map<String, Object> inputData) {
        return condition.getConditionText().equalsIgnoreCase((String) inputData.get("gender"));
    }

    private boolean PincodeCheck(ConditionModel condition, Map<String, Object> inputData) {
        String prefix = condition.getConditionText().replaceAll("[^0-9]", "");
        String pincode = inputData.get("pincode").toString();
        return pincode.startsWith(prefix);
    }

    private boolean DobCheck(ConditionModel condition, Map<String, Object> inputData) {
        String conditionText = condition.getConditionText();
        int age = (int) inputData.get("age");
        if (conditionText.contains("<")) {
            String[] parts = conditionText.split("<");
            return age < Integer.parseInt(parts[1]);
        } else if (conditionText.contains(">")) {
            String[] parts = conditionText.split(">");
            return age > Integer.parseInt(parts[1]);
        } else if (conditionText.contains("=")) {
            String[] parts = conditionText.split("=");
            return age == Integer.parseInt(parts[1]);
        } else {
            throw new IllegalArgumentException("Invalid condition: " + conditionText);
        }
    }

    private WorkflowstepModel determineNextStep(WorkflowstepModel step, Integer conditionResult) {
        List<WorkflowstepModel> steps = step.getWorkflow().getSteps();
        int currentIndex = steps.indexOf(step);
        return currentIndex+1 < steps.size()?  steps.get(currentIndex+1): null;
//        if (conditionResult>) {
//            return currentIndex + 1 < steps.size() ? steps.get(currentIndex + 1) : null;
//        } else {
//            return currentIndex + 2 < steps.size() ? steps.get(currentIndex + 2) : null;
//        }
    }

    private void logExecution(WorkflowresultModel workflowResult, WorkflowstepModel step, Integer conditionResult, Map<String, Object> inputData) {
        WorkflowstepresultModel logStep = new WorkflowstepresultModel();
        logStep.setWorkflowResult(workflowResult);
        logStep.setWorkflowStep(step);
        logStep.setActionResult(conditionResult>0 ? "CONDITION_TRUE" : "CONDITION_FALSE");
        logStep.setConditionResult(step.getCondition().getConditionText());

        logStep.setStopFlag(conditionResult);

        workflowstepresultRepository.save(logStep);

        List<WorkflowstepresultModel> steps = workflowResult.getSteps();
        if (steps == null) {
            steps = new ArrayList<>();
            workflowResult.setSteps(steps);
        }
        steps.add(logStep);
        workflowresultRepository.save(workflowResult);
    }
}