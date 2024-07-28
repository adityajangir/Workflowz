package com.example.Workflowz.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class WorkflowresultModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workflow_id")
    private WorkflowModel workflow;

    @OneToMany(mappedBy = "workflowResult", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkflowstepresultModel> steps;

    private boolean stopped;
}
