package com.example.team_project.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserJPARepository userJPARepository;

    public List<User> testUser() {
        return userJPARepository.findAll();
    }
}
