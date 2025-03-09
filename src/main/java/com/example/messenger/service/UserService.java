package com.example.messenger.service;

import com.example.messenger.model.User;
import com.example.messenger.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("User with this username " + user.getUsername() + " already exists.");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("User with this email " + user.getEmail() + " already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Integer id){
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }




    public void deleteUser(Integer id){
        if (!userRepository.existsById(id)) throw new RuntimeException("User with id " + id + " doesn`t exist.");
        userRepository.deleteById(id);
    }


}
