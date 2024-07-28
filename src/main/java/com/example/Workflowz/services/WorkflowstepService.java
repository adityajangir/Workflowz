package com.example.Workflowz.services;

import com.example.Workflowz.models.WorkflowstepModel;
import com.example.Workflowz.repository.WorkflowstepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkflowstepService {
    @Autowired
    private WorkflowstepRepository workflowstepRepository;

    public List<WorkflowstepModel> findAll() {
        return workflowstepRepository.findAll();
    }

    public Optional<WorkflowstepModel> findById(Long id) {
        return workflowstepRepository.findById(id);
    }

    public WorkflowstepModel save(WorkflowstepModel workflowstep) {
        return (WorkflowstepModel) workflowstepRepository.save(workflowstep);
    }

    public void deleteById(Long id) {
        workflowstepRepository.deleteById(id);
    }
}
