package com.mysql.mysql.repositories;

import com.mysql.mysql.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


// extends JpaRepository (or extendsCrudRepository - but is less clever, less methods)
// ->  contains methods for working with database
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}

