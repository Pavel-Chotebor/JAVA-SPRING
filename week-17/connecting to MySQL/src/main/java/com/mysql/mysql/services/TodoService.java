package com.mysql.mysql.services;

import com.mysql.mysql.models.Assignee;
import com.mysql.mysql.models.Todo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public interface TodoService {

    List<Todo> onlyActive ();

    List <Todo> allTodos();

    void saveTodo(Todo todo);

    List<Todo> searchBy_TITLE_DATE_DESCRIPTION(String searchInput);

    void deleteTodoById (Long id);

    Todo getTodoById (Long id);

    //List <Todo> allAssignedTodos (Long id);
}