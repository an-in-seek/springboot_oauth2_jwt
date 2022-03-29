package com.example.oauth2jwt.user.service;

import com.example.oauth2jwt.user.entity.User;
import com.example.oauth2jwt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
