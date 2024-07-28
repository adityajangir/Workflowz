package com.example.Workflowz.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class WorkflowstepresultModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workflow_result_id")
    private WorkflowresultModel workflowResult;

    @ManyToOne
    @JoinColumn(name = "workflow_step_id")
    private WorkflowstepModel workflowStep;

    private String actionResult;
    private String conditionResult;
    private Integer stopFlag;
}
