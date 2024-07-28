package com.example.Workflowz.controllers;

import com.example.Workflowz.models.ActivityModel;
import com.example.Workflowz.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public List<ActivityModel> getAllActivities() {
        return activityService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ActivityModel> getActivityById(@PathVariable Long id) {
        return activityService.findById(id);
    }

    @PostMapping
    public ActivityModel createActivity(@RequestBody ActivityModel activity) {
        return activityService.save(activity);
    }

    @PutMapping("/{id}")
    public ActivityModel updateActivity(@PathVariable Long id, @RequestBody ActivityModel activity) {
        activity.setId(id);
        return activityService.save(activity);
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable Long id) {
        activityService.deleteById(id);
    }
}
