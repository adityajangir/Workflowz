package com.example.Workflowz.repository;

import com.example.Workflowz.models.ActivityModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<ActivityModel, Long> {
}
