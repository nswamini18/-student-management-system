package com.swamini.service;

import com.swamini.exception.InvalidCredentials;
import com.swamini.model.User;
import com.swamini.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loginForApi(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidCredentials::new);
    }
}