package com.example.team_project.user._dto;

import lombok.Data;

@Data
public class UserRespDTO {

    public class UserDTO {
        private Integer userId;
        private String nickname;
        private String userImage;
    }
}
