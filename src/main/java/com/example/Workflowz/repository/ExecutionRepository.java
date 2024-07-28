package com.example.Workflowz.repository;

import com.example.Workflowz.models.ExecutionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionRepository extends JpaRepository<ExecutionModel, Long> {
}
