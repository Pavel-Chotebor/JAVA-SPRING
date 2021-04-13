package com.mysql.mysql.repositories;

import com.mysql.mysql.models.Assignee;
import com.mysql.mysql.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssigneeRepository extends JpaRepository<Assignee, Long> {
}