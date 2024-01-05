package com.example.team_project.user._dto;

import com.example.team_project.user.User;
import lombok.Data;

@Data
public class UserRespDTO {

    // ME 메인, 프로필 페이지 요청
    @Data
    public static class UserDTO {
        private String nickname;
        private String userImage;

        public UserDTO(User user) {
            this.nickname = user.getNickname();
            this.userImage = user.getUserImage();
        }
    }



}
