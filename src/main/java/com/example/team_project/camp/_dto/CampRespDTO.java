package com.example.team_project.camp._dto;

import com.example.team_project.camp.Camp;
import com.example.team_project.camp.camp_bookmark.CampBookmark;
import com.example.team_project.camp.camp_image.CampImage;
import com.example.team_project.camp.camp_rating.CampRating;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CampRespDTO {

    @Data
    public static class CampListDTO {
        private Integer id;
        private String campName;
        private String campAddress;
        private String campCallNumber;
        private String campWebsite;
        private String campRefundPolicy;
        private boolean campWater;
        private boolean campGarbageBag;
        private String holiday;
        private String campCheckIn;
        private String campCheckOut;
        private String campFieldImage;
        

    public CampListDTO(Integer id, String campName, String campAddress, String campCallNumber, String campWebsite,
                       String campRefundPolicy, boolean campWater, boolean campGarbageBag, String holiday, 
                       String campCheckIn, String campCheckOut, String campFieldImage) {
        this.id = id;
        this.campName = campName;
        this.campAddress = campAddress;
        this.campCallNumber = campCallNumber;
        this.campWebsite = campWebsite;
        this.campRefundPolicy = campRefundPolicy;
        this.campWater = campWater;
        this.campGarbageBag = campGarbageBag;
        this.holiday = holiday;
        this.campCheckIn = campCheckIn;
        this.campCheckOut = campCheckOut;
        this.campFieldImage = campFieldImage;
        }
    }
        


    // ME 캠핑 북마크 리스트 페이지 요청
    @Data
    public static class CampBookMarkListDTO {
        private List<CampBookmarkDTO> campBookmarkList;

        public CampBookMarkListDTO(List<CampBookmark> campBookmarkList) {
            this.campBookmarkList = campBookmarkList.stream().map(CampBookmarkDTO::new).collect(Collectors.toList());
        }

        @Data
        public static class CampBookmarkDTO {
            private Integer campId;
            private String campName;
            private String campAddress;
            private String campImage;
            private String campRating;

            public CampBookmarkDTO(CampBookmark campBookmark) {
                this.campId = campBookmark.getCamp().getId();
                this.campName = campBookmark.getCamp().getCampName();
                this.campAddress = campBookmark.getCamp().getCampAddress();
                this.campImage = campBookmark.getCamp().firstCampImage();
                this.campRating = campBookmark.getCamp().formatTotalRating();
            }
        }
    }
}
