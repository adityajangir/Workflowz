//package com.example.Workflowz.models;
//import jakarta.persistence.*;
//import lombok.Data;
//import org.hibernate.annotations.Cascade;
//
//
////import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//
//@Data
//@Entity
//public class WorkflowstepModel {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotNull
//    private String type; // "ACTION", "CONDITION", or "ACTIVITY"
//
//    @ManyToOne
////    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//    @JoinColumn(name = "workflow_id")
//    private WorkflowModel workflow;
//
//    @ManyToOne
//    @JoinColumn(name = "action_id")
//    private ActionModel action; // The action if this step represents an action
//
//    @ManyToOne
//    @JoinColumn(name = "condition_id")
//    private ConditionModel condition; // The condition if this step represents a condition
//
//    @ManyToOne
//    @JoinColumn(name = "activity_id")
//    private ActivityModel activity; // The activity if this step represents an activity
//
//    // Getters and Setters
//}

package com.example.Workflowz.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class WorkflowstepModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String type; // "ACTION", "CONDITION", or "ACTIVITY"

//    @ManyToOne
    @JoinColumn(name = "workflow_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private WorkflowModel workflow;

    @ManyToOne
    @JoinColumn(name = "action_id")
    private ActionModel action; // The action if this step represents an action

    @ManyToOne
    @JoinColumn(name = "condition_id")
    private ConditionModel condition; // The condition if this step represents a condition

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private ActivityModel activity; // The activity if this step represents an activity


    public WorkflowstepModel(WorkflowstepModel wsm) {
        this.id = wsm.id;
        this.type = wsm.type;
        this.action = wsm.action;
        this.condition = wsm.condition;
        this.activity = wsm.activity;
        this.workflow = wsm.workflow;
    }
}
