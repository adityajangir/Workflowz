package com.example.Workflowz.controllers;
import com.example.Workflowz.models.ConditionModel;
import com.example.Workflowz.services.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conditions")
public class ConditionController {

    @Autowired
    private ConditionService conditionService;

    @GetMapping
    public List<ConditionModel> getAllConditions() {
        return conditionService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ConditionModel> getConditionById(@PathVariable Long id) {
        return conditionService.findById(id);
    }

    @PostMapping
    public ConditionModel createCondition(@RequestBody ConditionModel condition) {
        return conditionService.save(condition);
    }

    @PutMapping("/{id}")
    public ConditionModel updateCondition(@PathVariable Long id, @RequestBody ConditionModel condition) {
        condition.setId(id);
        return conditionService.save(condition);
    }

    @DeleteMapping("/{id}")
    public void deleteCondition(@PathVariable Long id) {
        conditionService.deleteById(id);
    }
}
