package com.springandsql.sql.controllers;


import com.springandsql.sql.models.Todo;
import com.springandsql.sql.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


//



@Controller
public class TodoController {

    private TodoRepository todoRepository;

    @Autowired
    public TodoController (TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoController () {
    }

    @GetMapping ("/")
    public  String list (Model model) {


        model.addAttribute("todos", todoRepository.findAll());
        return "todoList";
    }

}