package com.egreenfox.trialexam.repositories;

import com.egreenfox.trialexam.models.SecretNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretNumberRepository extends JpaRepository <SecretNumber, Integer> {

}
