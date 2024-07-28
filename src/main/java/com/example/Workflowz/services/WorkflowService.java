package com.example.Workflowz.services;

import com.example.Workflowz.models.WorkflowModel;
import com.example.Workflowz.repository.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkflowService {
    @Autowired
    private WorkflowRepository workflowRepository;

    public List<WorkflowModel> findAll() {
        return workflowRepository.findAll();
    }

    public Optional<WorkflowModel> findById(Long id) {
        return workflowRepository.findById(id);
    }

    public WorkflowModel save(WorkflowModel workflow) {
        return (WorkflowModel) workflowRepository.save(workflow);
    }

    public void deleteById(Long id) {
        workflowRepository.deleteById(id);
    }

}
