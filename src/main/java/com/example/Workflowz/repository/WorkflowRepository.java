package com.example.Workflowz.repository;

import com.example.Workflowz.models.WorkflowModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkflowRepository extends JpaRepository<WorkflowModel, Long> {
}
