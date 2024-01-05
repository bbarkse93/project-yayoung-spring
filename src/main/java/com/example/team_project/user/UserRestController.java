package com.example.team_project.user;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.team_project._core.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserRestController {

    private final UserService userService;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body("TEST");
    }

    // me 페이지 요청
//    @GetMapping("/my-page")
//    public ResponseEntity<?> myPage() {
//        // @RequestHeader("Authorization") String token
//        // DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
//        // Integer userId = decodedJWT.getClaim("id")
//
//        return ResponseEntity.ok().body(user);
//    }
}
