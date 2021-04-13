package com.mysql.mysql.services;

import com.mysql.mysql.models.Assignee;
import com.mysql.mysql.models.Todo;
import com.mysql.mysql.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;


    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;

    }

    @Override
    public List<Todo> onlyActive() {

        List <Todo> activeTodos = todoRepository.findAll().stream()
                .filter (todo -> todo.getDone() == true)
                .collect(Collectors.toList());
        return activeTodos;
    }

    @Override
    public List<Todo> allTodos() {
        List<Todo> allTodos = new ArrayList<>();

         allTodos = todoRepository.findAll().stream()
                .collect(Collectors.toList());
         return allTodos;
    }


    @Override
    public void saveTodo(Todo todo) {
        todoRepository.save(todo);
    }


    @Override
    public List<Todo> searchBy_TITLE_DATE_DESCRIPTION(String searchInput) {
        List <Todo> filteredTodos = new ArrayList<>();
        searchInput.toLowerCase();

        filteredTodos = todoRepository.findAll().stream()
                .filter(todo -> todo.getTitle().toLowerCase().contains(searchInput)
                        || (todo.getId().toString().toLowerCase().contains(searchInput))
                        || (todo.getDescription().toLowerCase().contains(searchInput))
                        || (todo.getCreateDate().toLowerCase().contains(searchInput))
                        || (todo.getAssignee().getName().contains(searchInput)))
                .collect(Collectors.toList());
        return filteredTodos;
    }

    @Override
    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public Todo getTodoById(Long id) {
       return todoRepository.findById(id).orElse(null);

    }
}