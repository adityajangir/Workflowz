package com.example.Workflowz.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


//import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Data
@Entity
public class ActionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name; // e.g., "Notify User", "Archive Record"

    private String description; // Optional: Description of the action

    // Getters and Setters
}
