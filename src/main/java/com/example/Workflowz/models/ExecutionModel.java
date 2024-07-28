package com.example.Workflowz.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class ExecutionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workflow_id")
    private WorkflowModel workflow;

    @ManyToOne
    @JoinColumn(name = "step_id")
    private WorkflowstepModel step;

    private LocalDateTime executionTime;
    private String outcome;
    private String details;
}
