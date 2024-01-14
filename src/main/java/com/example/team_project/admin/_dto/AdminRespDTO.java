package com.example.team_project.admin._dto;

import com.example.team_project.camp.Camp;
import com.example.team_project.camp.camp_rating.CampRating;
import com.example.team_project.camp.camp_review.CampReview;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class AdminRespDTO {

    // 캠핑장 목록 DTO
    @Data
    public static class CampDTO {
        private Integer campId;
        private String campName;
        private String campAddress;
        private String campNumber;

        public CampDTO(Camp camp) {
            this.campId = camp.getId();
            this.campName = camp.getCampName();
            this.campAddress = camp.getCampAddress();
            this.campNumber = camp.getCampCallNumber();
        }
    }

    // 캠핑장 현황 DTO
    @Data
    public static class RatingCampDTO {
        private Integer campId;
        private String campName;
        private String campAddress;
        private String campTotalRating;
        private Integer orderCount;

        public RatingCampDTO(Camp camp) {
            this.campId = camp.getId();
            this.campName = camp.getCampName();
            this.campAddress = camp.getCampAddress();
            this.campTotalRating = camp.formatTotalRating();
            this.orderCount = (int) camp.getCampFieldList().stream().filter(c -> c.getOrder() != null).count();
        }
    }

    // 캠핑장 리뷰 DTO
    @Data
    public static class CampReviewDTO {
        private Integer campId;
        private String campName;
        private String campTotalRating;
        private double cleanliness;
        private double managementness;
        private double friendliness;
        private List<ReviewDTO> reviewDTOList;

        public CampReviewDTO(Camp camp, List<CampReview> campReviewList) {
            this.campId = camp.getId();
            this.campName = camp.getCampName();
            this.campTotalRating = camp.formatTotalRating();
            this.cleanliness = Double.parseDouble(camp.formatRating(camp.getCampRatingList().stream().map(CampRating::getCleanliness).collect(Collectors.toList())));
            this.managementness = Double.parseDouble(camp.formatRating(camp.getCampRatingList().stream().map(CampRating::getManagementness).collect(Collectors.toList())));
            this.friendliness = Double.parseDouble(camp.formatRating(camp.getCampRatingList().stream().map(CampRating::getFriendliness).collect(Collectors.toList())));
            this.reviewDTOList = campReviewList.stream().map(ReviewDTO::new).collect(Collectors.toList());
        }

        @Data
        public static class ReviewDTO {
            private Integer reviewId;
            private String userNickname;
            private String content;
            private String totalRating;
            private String createAt;

            public ReviewDTO(CampReview campReview) {
                this.reviewId = campReview.getId();
                this.userNickname = campReview.getUser().getNickname();
                this.content = campReview.getContent();
                this.totalRating = campReview.getCampRating().formatTotal();
                this.createAt = campReview.formatTime();
            }
        }
    }
}