package com.example.team_project.admin._dto;

import lombok.Data;

public class AdminReqDTO {

    // login 수정
    @Data
    public static class LoginDTO{
        private String username;
        private String password;
    }

    // faq 등록
    @Data
    public static class SaveFaqDTO{
        private String title;
        private String content;
        private Integer boardCategoryId;
        private Integer userId;
    }

    // faq 수정
    @Data
    public static class UpdateFaqDTO{
        private String title;
        private String content;
        private Integer boardCategoryId;
        private Integer userId;
    }

    // notice 등록
    @Data
    public static class SaveNoticeDTO{
        private String title;
        private String content;
        private Integer userId;
    }

    // notice 수정
    @Data
    public static class UpdateNoticeDTO{
        private String title;
        private String content;
        private Integer userId;
    }


}
