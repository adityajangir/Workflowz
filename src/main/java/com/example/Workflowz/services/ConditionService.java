package com.example.Workflowz.services;
import com.example.Workflowz.models.ConditionModel;
import com.example.Workflowz.repository.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConditionService {
    @Autowired
    private ConditionRepository conditionRepository;

    public List<ConditionModel> findAll() {
        return conditionRepository.findAll();
    }

    public Optional<ConditionModel> findById(Long id) {
        return conditionRepository.findById(id);
    }

    public ConditionModel save(ConditionModel condition) {
        return (ConditionModel) conditionRepository.save(condition);
    }

    public void deleteById(Long id) {
        conditionRepository.deleteById(id);
    }
}
