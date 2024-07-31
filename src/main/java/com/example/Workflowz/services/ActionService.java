package com.example.Workflowz.services;

import com.example.Workflowz.models.ActionModel;
import com.example.Workflowz.models.ConditionModel;
import com.example.Workflowz.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class ActionService {
    @Autowired
    private ActionRepository actionRepository;

    public List<ActionModel> findAll() {
        return actionRepository.findAll();
    }

    public Optional<ActionModel> findById(Long id) {
        return actionRepository.findById(id);
    }

    public ActionModel save(ActionModel action) {
        return (ActionModel) actionRepository.save(action);
    }

    public void deleteById(Long id) {
        actionRepository.deleteById(id);
    }

}
