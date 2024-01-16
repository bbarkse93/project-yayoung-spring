package com.example.team_project.admin._dto;

import com.example.team_project.option_management.OptionManagement;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    // camp 등록
    @Data
    public static class SaveCampDTO {
        private String campName;
        private String campAddress;
        private String campCallNumber;
        private String campWebsite;
        private String holiday;
        private String campCheckIn;
        private String campCheckOut;
        private boolean campWater;
        private boolean campGarbageBag;
        private String campRefundPolicy;
        private List<MultipartFile> campPhotoList;
        private List<OptionDTO> campOptionDTOList;
        private MultipartFile campFieldImage;
        private List<CampFieldDTO> campFieldDTOList;


        @Data
        public static class OptionDTO{
            private Integer optionId;
        }

        @Data
        public static class CampFieldDTO{
            private String fieldName;
            private Integer price;
        }
    }
}
