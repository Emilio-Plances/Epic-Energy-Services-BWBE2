package com.example.Epic.Energy.Services.services;

import com.example.Epic.Energy.Services.entities.User;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User findByUsername(String username) throws NotFoundException {
        Optional<User> optionalUser=userRepository.findByUsername(username);
        if(optionalUser.isEmpty())throw new NotFoundException("User not found");
        return optionalUser.get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new NotFoundException("User not found"));
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public void deleteUser(Long id) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}
