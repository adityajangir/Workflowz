package com.example.Workflowz.repository;

import com.example.Workflowz.models.ActionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<ActionModel, Long> {
}
