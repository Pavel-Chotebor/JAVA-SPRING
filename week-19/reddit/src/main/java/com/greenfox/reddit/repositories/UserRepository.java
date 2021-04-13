package com.greenfox.reddit.repositories;

import com.greenfox.reddit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    @Query(value = "SELECT u from User u where u.username = :username")
    User findUserByName (String username);

}
