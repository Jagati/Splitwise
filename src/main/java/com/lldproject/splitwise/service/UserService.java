package com.lldproject.splitwise.service;

import com.lldproject.splitwise.exception.UserAlreadyExistsException;
import com.lldproject.splitwise.exception.UserNotFoundException;
import com.lldproject.splitwise.model.User;
import com.lldproject.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User signup(String name, String email, String password) throws UserAlreadyExistsException {
        Optional<User> userOp = userRepository.findByEmail(email);
        if(userOp.isPresent()){
            throw new UserAlreadyExistsException("User already exists!");
        }
        //Create a new user
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }
    public boolean login(String email, String password) throws UserNotFoundException {
        Optional<User> userOp = userRepository.findByEmail(email);
        if(userOp.isEmpty()){
            throw new UserNotFoundException("User not found!");
        }
        User user = userOp.get();
        return user.getPassword().equals(password);
    }
}
