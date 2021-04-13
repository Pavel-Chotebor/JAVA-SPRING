package com.greenfox.reddit.services;

import com.greenfox.reddit.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    boolean isNameAlreadyExist (String name );

    void saveUser (User user);

    User getUserById (Long id);

    User getUserByName (String name);
}
