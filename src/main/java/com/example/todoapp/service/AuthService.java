package com.example.todoapp.service;

import com.example.todoapp.model.User;
import com.example.todoapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(String username, String password){
        User user = new User();
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);

        userRepository.save(user);
        return "registered successfully";
    }

    public String login(String username, String password){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Wrong password");
        }
        return jwtService.generateToken(username);
    }
}
