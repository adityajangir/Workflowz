package com.example.Workflowz.models;




import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;
//import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Component
@Data
@Entity
public class WorkflowModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;


    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workflow", cascade = CascadeType.ALL)
    private List<WorkflowstepModel> steps; // Steps in the workflow

}
