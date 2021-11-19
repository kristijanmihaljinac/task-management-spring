package com.kristijanmihaljinac.taskmanagementspring.service;

import com.kristijanmihaljinac.taskmanagementspring.domain.User;
import com.kristijanmihaljinac.taskmanagementspring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<User> findAll() {
        return userRepository.findAll()
                .stream()
                .collect(Collectors.toUnmodifiableSet());
    }
}
