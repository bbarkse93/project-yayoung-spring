package com.example.team_project.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<User, Integer> {

    //ㅈㅇㅈ
    Optional<User> findByUsername(String username);

}
