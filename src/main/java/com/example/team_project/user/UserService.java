package com.example.team_project.user;

import com.example.team_project._core.errors.exception.Exception404;
import com.example.team_project._core.utils.ImageUtils;
import com.example.team_project._core.utils.JwtTokenUtils;
import com.example.team_project.user._dto.UserReqDTO;
import com.example.team_project.user._dto.UserRespDTO;
import com.example.team_project.user.token.TokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserJPARepository userJPARepository;

    @Value("${TENCO_KEY}")
    private String tencoKey;

    // ME 페이지 요청
    public UserRespDTO.UserDTO myPage(Integer userId) {
        User user = userJPARepository.findById(userId).orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다." + userId));
        return new UserRespDTO.UserDTO(user);
    }

    // ME 프로필 페이지 요청
    public UserRespDTO.UserDTO proflieDetail(Integer userId) {
        User user = userJPARepository.findById(userId).orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다." + userId));
        return new UserRespDTO.UserDTO(user);
    }

    // ME 프로필 수정
    @Transactional
    public UserRespDTO.UserDTO profileUpdate(UserReqDTO.ProfileUpdateDTO requestDTO, Integer userId) {

        System.out.println("유저 업데이트 서비스 진입 ");
        User user = userJPARepository.findById(userId).orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다." + userId));

        System.out.println("아이디 조회 잘해옴? " + user.toString());
        // 사진 디코딩
        if (!requestDTO.getUserImage().startsWith(requestDTO.getNickname())) {
            System.out.println("조건문 발동 " + user.getUserImage());
            String decodeImage = ImageUtils.decodeImage(requestDTO.getUserImage(), requestDTO.getNickname());
            user.updateNickname(requestDTO.getNickname());
            user.updateUserImage("/images/user/" + decodeImage);
          //  System.out.println("조건문 발동 잘바뀜? " + userOP.getNickname());
            return new UserRespDTO.UserDTO(user);
        }

        user.updateNickname(requestDTO.getNickname());
     //   System.out.println("조건문 지남 " + userOP.getUserImage());
      // System.out.println("조건문 지남 " + userOP.getNickname());

        return new UserRespDTO.UserDTO(user);
    }

    // ME 회원 탈퇴
    @Transactional
    public UserRespDTO.withDrawDTO withDraw(Integer userId) {
        User user = userJPARepository.findById(userId).orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다." + userId));
        user.updateIsWithDraw(true);
        return new UserRespDTO.withDrawDTO(user);
    }

    public UserRespDTO.LoginDTO kakaoLogin(UserRespDTO.KakaoProfile dto) {

        // 유저 조회
        User user = userJPARepository.findByUsername(dto.getId());


        // 유저가 없으면 회원가입 시킨다.
        if (user == null) {
           User userOP = userJPARepository.save(dto.toEntity(tencoKey));
            // 토큰 생성
            String jwt = JwtTokenUtils.create(userOP);
           return new UserRespDTO.LoginDTO(userOP, jwt);
        } else {
            // 토큰 생성
            String jwt = JwtTokenUtils.create(user);
            return new UserRespDTO.LoginDTO(user, jwt);
        }
    }

    public UserRespDTO.LoginDTO naverLogin(UserRespDTO.NaverProfile dto) {
        User user = userJPARepository.findByUsername(dto.getResponse().getId());


        // 유저가 없으면 회원가입 시킨다.
        if (user == null) {
            User userOP = userJPARepository.save(dto.toEntity(tencoKey));
            // 토큰 생성
           String jwt = JwtTokenUtils.create(userOP);
            return new UserRespDTO.LoginDTO(userOP, jwt);
        } else {
            // 토큰 생성
            String jwt = JwtTokenUtils.create(user);
            return new UserRespDTO.LoginDTO(user, jwt);
        }
    }

    // Base64 데이터 여부를 확인하는 메서드
    private boolean isBase64Data(String data) {
        // 정규표현식으로 "data:image"로 시작하는지 확인
        String base64Pattern = "^data:image\\/([a-zA-Z]+);base64,.*$";
        return Pattern.matches(base64Pattern, data);
    }
}
