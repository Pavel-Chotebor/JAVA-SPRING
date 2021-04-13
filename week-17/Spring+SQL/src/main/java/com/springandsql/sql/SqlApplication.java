package com.springandsql.sql;

import com.springandsql.sql.controllers.TodoController;
import com.springandsql.sql.models.Todo;
import com.springandsql.sql.repositories.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqlApplication implements CommandLineRunner {

    private TodoRepository todoRepository;

    public SqlApplication (TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    public static void main(String[] args)  {
        SpringApplication.run(SqlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception { //adding from JAVA to database
        todoRepository.save(new Todo("I have to learn Object Relational Mapping"));
        todoRepository.save(new Todo("I have to learn SQL"));

    }
}
