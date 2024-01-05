package com.example.team_project.user;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.team_project._core.utils.ApiUtils;
import com.example.team_project._core.utils.JwtTokenUtils;
import com.example.team_project.user._dto.UserReqDTO;
import com.example.team_project.user._dto.UserRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // ME 메인 페이지 요청
    // localhost:8080/user/my-page
    @GetMapping("/my-page")
    public ResponseEntity<?> myPage() {
        // @RequestHeader("Authorization") String token
        // DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        // Integer userId = decodedJWT.getClaim("id")
        UserRespDTO.UserDTO responseDTO = userService.myPage(1);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // ME 프로필 페이지 요청
    // localhost:8080/user/my-page/profile
    @GetMapping("/my-page/profile")
    public ResponseEntity<?> proflieDetail() {
        // @RequestHeader("Authorization") String token
        // DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        // Integer userId = decodedJWT.getClaim("id")
        UserRespDTO.UserDTO responseDTO = userService.proflieDetail(1);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }


    // ME 프로필 수정
    // localhost:8080/user/my-page/profile
    @PutMapping("/my-page/profile")
    public ResponseEntity<?> profileUpdate(@RequestBody UserReqDTO.ProfileUpdateDTO requestDTO){
        // @RequestHeader("Authorization") String token
        // DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        // Integer userId = decodedJWT.getClaim("id")
        System.out.println("내부 : " + requestDTO);
        UserRespDTO.UserDTO responseDTO = userService.profileUpdate(requestDTO, 1);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }


}
