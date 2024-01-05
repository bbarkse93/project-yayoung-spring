package com.example.team_project.user;

import com.example.team_project._core.erroes.exception.Exception404;
import com.example.team_project._core.utils.ImageUtils;
import com.example.team_project.user._dto.UserReqDTO;
import com.example.team_project.user._dto.UserRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserJPARepository userJPARepository;

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
        User user = userJPARepository.findById(userId).orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다." + userId));
        // 사진 디코딩
        String decodeImage = ImageUtils.encodeImage(requestDTO.getUserImage(), requestDTO.getNickname());
        user.updateNickname(requestDTO.getNickname());
        user.updateUserImage(decodeImage);
        return new UserRespDTO.UserDTO(user);
    }




}
