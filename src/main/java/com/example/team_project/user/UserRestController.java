package com.example.team_project.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserRestController {

    private final UserService userService;

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok().body("TEST");
    }
}
