package com.mysql.mysql;

import com.mysql.mysql.models.Assignee;
import com.mysql.mysql.models.Todo;
import com.mysql.mysql.repositories.AssigneeRepository;
import com.mysql.mysql.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MysqlApplication implements CommandLineRunner {

    private TodoRepository todoRepository;
    private AssigneeRepository assigneeRepository;

    @Autowired
    public MysqlApplication(TodoRepository todoRepository, AssigneeRepository assigneeRepository) {
        this.todoRepository = todoRepository;
        this.assigneeRepository = assigneeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MysqlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {   //Adding from JAVA to database
/*

        Todo todo1 = new Todo("go out", true, true,"go out do out go out...");
        Todo todo2 = new Todo("I have to learn Object Relational Mapping", true, false, "lear lear lear something");
        Todo todo3 = new Todo("I have to learn SQL", false, true, "SQL SQL SQLSQL");

        Assignee assignee1 = new Assignee("person1", "person3@seznam.cz");
        Assignee assignee2 = new Assignee("person2","cool.email@gmail.com");
        Assignee assignee3 = new Assignee("person3","cool2.email@gmail.com");

        assigneeRepository.save(assignee1);
        assigneeRepository.save(assignee2);
        assigneeRepository.save(assignee3);

        todo1.setAssignee(assignee1);
        todo2.setAssignee(assignee3);
        todo3.setAssignee(assignee2);

        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);

 */
    }
}
