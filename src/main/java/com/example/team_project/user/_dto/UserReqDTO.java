package com.example.team_project.user._dto;

import com.example.team_project.user.User;
import lombok.Data;

@Data
public class UserReqDTO {

    // ME 프로필 페이지 수정 요청
    @Data
    public static class ProfileUpdateDTO {
        private String nickname;
        private String userImage;
    }
}
