package com.example.Workflowz.services;

import com.example.Workflowz.models.ActivityModel;
import com.example.Workflowz.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public List findAll() {
        return activityRepository.findAll();
    }

    public Optional<ActivityModel> findById(Long id) {
        return activityRepository.findById(id);
    }

    public ActivityModel save(ActivityModel activity) {
        return (ActivityModel) activityRepository.save(activity);
    }

    public void deleteById(Long id) {
        activityRepository.deleteById(id);
    }
}
