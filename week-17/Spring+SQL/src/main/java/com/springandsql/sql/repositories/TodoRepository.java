package com.springandsql.sql.repositories;


import com.springandsql.sql.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository <Todo, Long> {





}
