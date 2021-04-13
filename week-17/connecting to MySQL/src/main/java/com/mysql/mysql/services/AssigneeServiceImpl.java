package com.mysql.mysql.services;
import com.mysql.mysql.models.Assignee;
import com.mysql.mysql.models.Todo;
import com.mysql.mysql.repositories.AssigneeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AssigneeServiceImpl implements AssigneeService{

    private AssigneeRepository assigneeRepository;

    public AssigneeServiceImpl (AssigneeRepository assigneeRepository) {
        this.assigneeRepository = assigneeRepository;
    }

    @Override
    public List<Assignee> allAssignee() {
        List<Assignee> assignees = new ArrayList<>();

        assignees = assigneeRepository.findAll().stream().collect(Collectors.toList());
        return assignees;
    }

    @Override
    public void saveAssignee(Assignee assignee) {
        assigneeRepository.save(assignee);
    }

    @Override
    public void deleteAssignee(Long id) {
        assigneeRepository.deleteById(id);

    }

    @Override
    public Assignee getAssigneeById(Long id) {
        return assigneeRepository.findById(id).orElse(null);
    }
}