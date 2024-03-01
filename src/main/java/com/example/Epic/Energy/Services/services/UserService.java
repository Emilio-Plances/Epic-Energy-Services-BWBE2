package com.example.Epic.Energy.Services.services;

import com.example.Epic.Energy.Services.entities.User;
import com.example.Epic.Energy.Services.enums.Role;
import com.example.Epic.Energy.Services.exceptions.AlreadyAdminException;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.repositories.UserRepository;
import com.example.Epic.Energy.Services.requests.RegisterRequest;
import com.example.Epic.Energy.Services.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    public User findByUsername(String username) throws NotFoundException {
        Optional<User> optionalUser=userRepository.findByUsername(username);
        if(optionalUser.isEmpty())throw new NotFoundException("User not found");
        return optionalUser.get();
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getUserById(long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User with id= " + id + " was not found"));
    }
    public User saveUser(RegisterRequest registerRequest) {
        User x = new User();
        x.setUsername(registerRequest.getUsername());
        x.setEmail(registerRequest.getEmail());
        x.setPassword(encoder.encode(registerRequest.getPassword()));
        x.setFirstName(registerRequest.getFirstName());
        x.setLastName(registerRequest.getLastName());
        x.setRoles(Set.of(Role.USER));
        return userRepository.save(x);
    }

    public User updateUser(long id, UserRequest userRequest) throws NotFoundException {
        User x = getUserById(id);
        x.setUsername(userRequest.getUsername());
        x.setEmail(userRequest.getEmail());
        x.setFirstName(userRequest.getFirstName());
        x.setLastName(userRequest.getLastName());

        return userRepository.save(x);
    }

    public void deleteUser(Long id) throws NotFoundException {
        User x = getUserById(id);
        userRepository.delete(x);
    }

    public User uploadAvatar(long id, String url) throws NotFoundException{
        User x = getUserById(id);
        x.setAvatar(url);
        return userRepository.save(x);
    }

    public void updateUserToAdmin(Long userId) throws NotFoundException, AlreadyAdminException {
        User user = getUserById(userId);
        if(user.getRoles().contains(Role.ADMIN)) throw new AlreadyAdminException("Already admin");
        user.addRole(Role.ADMIN);
        userRepository.save(user);
    }
}
