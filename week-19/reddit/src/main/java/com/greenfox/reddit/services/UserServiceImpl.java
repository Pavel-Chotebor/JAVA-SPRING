package com.greenfox.reddit.services;

import com.greenfox.reddit.models.User;
import com.greenfox.reddit.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isNameAlreadyExist(String name) {
        if (userRepository.findUserByName(name) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public User getUserByName(String name) {
        if (userRepository.findUserByName(name) != null) {
            return userRepository.findUserByName(name);
        } else {
            return null;
        }
    }
}
