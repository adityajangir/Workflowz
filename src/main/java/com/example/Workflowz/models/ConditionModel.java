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

    // Getters and Setters
}
