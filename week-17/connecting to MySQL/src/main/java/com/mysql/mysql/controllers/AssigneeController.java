package com.mysql.mysql.controllers;


import com.mysql.mysql.models.Assignee;
import com.mysql.mysql.models.Todo;
import com.mysql.mysql.services.AssigneeService;
import com.mysql.mysql.services.AssigneeServiceImpl;
import com.mysql.mysql.services.TodoService;
import com.mysql.mysql.services.TodoServiceImpl;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AssigneeController {

    private AssigneeService assigneeService;
    private TodoService todoService;

    public AssigneeController(AssigneeService assigneeService, TodoService todoService) {
        this.assigneeService = assigneeService;
        this.todoService = todoService;
    }

    @GetMapping ("assigneeList")
    public String showAssigneeList (Model model) {
        model.addAttribute("assignees", assigneeService.allAssignee());
        return "assignees";
    }

    @GetMapping("/{id}/deleteAssignee")  // deleting by id from database
    public String deleteAssigneeProcessing (@PathVariable Long id) {
        assigneeService.deleteAssignee(id);
        return "redirect:/";
    }

    @GetMapping ("/addNewAssignee")
    public String addNewAssignee () {
        return "addAssignee";
    }

    @PostMapping ("/addNewAssigneeProcessing")
    public String addNewAssigneeProcessing (@ModelAttribute Assignee assignee){
        assigneeService.saveAssignee(assignee);
        return "redirect:/assigneeList";
    }


    @GetMapping ("/{id}/editAssignee")
    public String showEditAssigneeForm (@PathVariable Long id) {
        return "assigneeEditForm";
    }

    @PostMapping("/{id}/assigneeEditFormProcessing")
    public String assigneeEditFormProcessing (@ModelAttribute Assignee assignee) {
        assigneeService.saveAssignee(assignee);
        return "redirect:/assigneeList";
    }

    @GetMapping ("/{id}/detailAssignee")
    public String showDetailAssignee (Model model, @PathVariable Long id, @ModelAttribute Todo todo) {
        model.addAttribute("detailAssignee", assigneeService.getAssigneeById(id));
        List<String> title = assigneeService.getAssigneeById(id).getTodos().stream()
                .map(t -> t.getTitle())
                .collect(Collectors.toList());

        model.addAttribute("todosForPerson", title);

        return "detailAssignee";
    }

}
