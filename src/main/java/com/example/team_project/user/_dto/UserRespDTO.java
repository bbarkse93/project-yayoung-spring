package com.example.team_project.user._dto;

import com.example.team_project.user.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.apache.coyote.Response;

@Data
public class UserRespDTO {

    // ME 메인, 프로필 페이지 요청
    @Data
    public static class UserDTO {
        private Integer userId;
        private String nickname;
        private String userImage;

        public UserDTO(User user) {
            this.userId = user.getId();
            this.nickname = user.getNickname();
            this.userImage = user.getUserImage();
        }
    }

    @Data
    public static class LoginDTO {
        private UserLoginDTO user;
        private String token;

        public LoginDTO(User user, String token) {
            this.user = new UserLoginDTO(user);
            this.token = token;
        }

        @Data
        public static class UserLoginDTO {
            private String username;
            private String nickname;
            private String userImage;

            public UserLoginDTO(User user) {
                this.username = user.getUsername();
                this.nickname = user.getNickname();
                this.userImage = user.getUserImage();
            }
        }
    }

    // ME 회원 탈퇴
    @Data
    public static class withDrawDTO {
        private Integer userId;
        private String nickname;
        private boolean isWithdraw;

        public withDrawDTO(User user) {
            this.userId = user.getId();
            this.nickname = user.getNickname();
            this.isWithdraw = user.isWithdraw();
        }
    }

    @Data
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class KakaoProfile {
        private String id;
        private String connectedAt;
        private Propreties properties;

        @Data
        @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
        public static class Propreties {
            private String nickname;
            private String profileImage;
            private String thumbnailImage;

        }
        public User toEntity(String password) {
            return User.builder()
                    .username(id)
                    .password(password)
                    .nickname(properties.nickname)
                    .userImage(properties.profileImage)
                    .build();
        }

    }

    @Data
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class NaverProfile {
        private String resultcode;
        private String message;
        private Profile response;

        @Data
        @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
        public static class Profile {
            private String id;
            private String profileImage;
            private String name;

        }
        public User toEntity(String password) {
            return User.builder()
                    .username(response.id)
                    .password(password)
                    .nickname(response.name)
                    .userImage(response.profileImage)
                    .build();
        }
    }



}
