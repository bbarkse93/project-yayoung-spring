package com.example.team_project.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_tb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false, unique = true)
    private String username; // 인증시 필요한 필드

    @Column(length = 256, nullable = false)
    private String password;

    @Column(length = 256, nullable = false)
    private String email;

    @CreationTimestamp
    private Timestamp userCreatedAt;

    @Builder
    public User(Integer id, String username, String password, String email, Timestamp userCreatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userCreatedAt = userCreatedAt;
    }
}