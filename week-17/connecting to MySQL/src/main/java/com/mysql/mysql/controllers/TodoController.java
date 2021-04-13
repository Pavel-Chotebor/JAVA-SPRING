package com.mysql.mysql.controllers;

import com.mysql.mysql.models.Todo;
import com.mysql.mysql.repositories.AssigneeRepository;
import com.mysql.mysql.repositories.TodoRepository;
import com.mysql.mysql.services.AssigneeService;
import com.mysql.mysql.services.AssigneeServiceImpl;
import com.mysql.mysql.services.TodoService;
import com.mysql.mysql.services.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/*Making a connection between Java and MySQL -> add properties to application.properties
  in this line:  spring.datasource.url=jdbc:mysql://localhost:3306/todo?serverTimezone=UTC  todo after localhost
  is name of the table, which must be created before like new schema in MySQL with this name
 */

@Controller
public class TodoController {

    private TodoService todoService;
    private AssigneeService assigneeService;
    private TodoRepository todoRepository;


    @Autowired
    public TodoController(TodoService todoService, AssigneeService assigneeService, TodoRepository todoRepository ) {
        this.todoService = todoService;
        this.assigneeService = assigneeService;
        this.todoRepository =todoRepository;
    }

    public TodoController () {
    }
//// ////
                                                  // this way is possible to have 2 path to same endopoint
    @GetMapping({"/", "/list"})                   // when is active true -> printing only with done=true, using stream in service
    public  String list (Model model, @RequestParam (required = false) boolean isActive, @RequestParam(required = false) String searchInput) {
        if (isActive == true) {
            model.addAttribute("todos", todoService.onlyActive());
        }
        else if(searchInput==null) {
            model.addAttribute("assignees", assigneeService.allAssignee());
            model.addAttribute("todos", todoService.allTodos());
        }
        else if (searchInput!=null) {
            model.addAttribute("todos",todoService.searchBy_TITLE_DATE_DESCRIPTION(searchInput));
        }
        return "todoList";
    }


    @GetMapping("/add")  // adding form
    public  String addTodo () {
        return "add";
    }


    @PostMapping("addProcessing")  // saving newTodo to database (done and urgent parameter is false by default)
        public String addProcessing (Model model, @RequestParam String newTodo) {
             todoService.saveTodo(new Todo(newTodo));
            return "redirect:/";
        }


    @GetMapping("/{id}/delete")  // deleting by id from database
    public String deleteProcessing (@PathVariable Long id) {
        //todoRepository.deleteById(id);
        todoService.deleteTodoById(id);
        return "redirect:/";
    }


    @GetMapping("/{id}/edit")  //adding form
    public  String showEditForm (@PathVariable Long id, Model model) {
        model.addAttribute("assignees", assigneeService.allAssignee());
        return "todoEditForm";
    }


    @PostMapping("/{id}/editFormProcessing")
    public String editFormProcessing (@ModelAttribute Todo todo, @RequestParam Long assigneeId) {
        todo.setAssignee(assigneeService.getAssigneeById(assigneeId));
        todoService.saveTodo(todo);
        //todo.setAssignee(assigneeRepository.findById(assigneeId).get());
        return "redirect:/";
    }


    @GetMapping("/{id}/detail")  //adding form
    public  String showDetailTodo (Model model, @PathVariable Long id) {
        //model.addAttribute("detailTodo",todoRepository.getOne(id));
        model.addAttribute("detailTodo",todoService.getTodoById(id));
        return "detailTodo";
    }

    @PostMapping("search")
    public String search (@RequestParam String searchInput, Model model) {
        model.addAttribute("filteredList",todoService.searchBy_TITLE_DATE_DESCRIPTION(searchInput));
        return "todoList";
    }
}

/*

spring.datasource.url=jdbc:mysql://localhost:3306/todo?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=sqlpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
spring.logging.level.org.hibernate.SQL=debug
spring.jpa.show-sql=true


 */



