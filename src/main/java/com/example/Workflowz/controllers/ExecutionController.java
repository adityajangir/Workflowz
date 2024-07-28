package com.example.Workflowz.controllers;


import com.example.Workflowz.models.WorkflowstepresultModel;
import com.example.Workflowz.services.ExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


//
//import javafx.util.Pair; // Use this if you are on Java 17 or later
//
//private Map<String, List<Pair<String, Number>>> generateGraph(List<WorkflowstepresultModel> stepResults) {
//    Map<String, List<Pair<String, Number>>> graph = new HashMap<>();
//
//    for (WorkflowstepresultModel result : stepResults) {
//        String actionName = result.getAction().getName();
//        String activityName = result.getActivity().getName();
//        Number flag = result.isStopFlag() ? 1 : 0; // Assuming 1 for stopped and 0 for not stopped
//
//        graph.putIfAbsent(actionName, new ArrayList<>());
//        graph.get(actionName).add(new Pair<>(activityName, flag));
//    }
//
//    return graph;
//}
//
//public ResponseEntity<Map<String, List<Pair<String, Number>>>> getWorkflowGraph(Long workflowId) {
//    List<WorkflowstepresultModel> stepResults = getExecutionLogs(workflowId);
//    Map<String, List<Pair<String, Number>>> graph = generateGraph(stepResults);
//    return ResponseEntity.ok(graph);
//}



@RestController
@RequestMapping("/execution")
public class ExecutionController {

    @Autowired
    private ExecutionService executionService;

    @PostMapping("/start/{workflowId}")
    public void startWorkflow(@PathVariable Long workflowId, @RequestBody Map<String, Object> input) {
        executionService.startWorkflow(workflowId, input);
    }



}
