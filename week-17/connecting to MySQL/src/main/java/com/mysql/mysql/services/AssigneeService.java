package com.mysql.mysql.services;
import com.mysql.mysql.models.Assignee;
import com.mysql.mysql.models.Todo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface AssigneeService {

    List<Assignee> allAssignee();

    void saveAssignee(Assignee assignee);

    void deleteAssignee(Long id);

    Assignee getAssigneeById (Long id);

}