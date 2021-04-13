package com.greenfox.restapi.repositories;

import com.greenfox.restapi.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository <Log, Long> {
}
