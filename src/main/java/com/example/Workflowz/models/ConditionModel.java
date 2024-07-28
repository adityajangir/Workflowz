package com.example.Workflowz.models;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
@Entity
public class ConditionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String conditionText; // e.g., "If user is admin", "If report is overdue"
//
//    @ManyToOne
//    @JoinColumn(name = "action_id")
//    private ActionModel action; // Action to be taken if the condition is met
//
//    @ManyToOne
//    @JoinColumn(name = "workflow_id")
//    private WorkflowModel workflow; // Workflow this condition is part of

    // Getters and Setters
}
