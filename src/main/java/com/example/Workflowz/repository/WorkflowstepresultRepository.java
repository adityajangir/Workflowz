package com.example.Workflowz.repository;

import com.example.Workflowz.models.WorkflowstepresultModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkflowstepresultRepository extends JpaRepository<WorkflowstepresultModel, Long> {

    List<WorkflowstepresultModel> findByWorkflowResult_Id(Long id);
}
