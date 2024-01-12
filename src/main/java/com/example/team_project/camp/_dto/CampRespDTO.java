package com.example.team_project.camp._dto;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import com.example.team_project._core.utils.TimestampUtils;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.camp_bookmark.CampBookmark;
import com.example.team_project.camp.camp_rating.CampRating;
import com.example.team_project.camp.camp_review.CampReview;
import com.example.team_project.camp_field.CampField;
import com.example.team_project.order.Order;
import com.example.team_project.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
public class CampRespDTO {

    // 전우진 240108
    // 캠핑장 목록 페이지
    @Data
    public static class CampListDTO {
        private List<CampDTO> campDTOList;

        public CampListDTO(List<Camp> campList) {
            this.campDTOList = campList.stream().map(CampDTO::new).collect(Collectors.toList());
        }

        @Data
        public static class CampDTO {
            private Integer id;
            private String campName;
            private String campAddress;
            private String campImage;
            private String campRating;

            public CampDTO(Camp camp) {
                this.id = camp.getId();
                this.campName = camp.getCampName();
                this.campAddress = camp.getCampAddress();
                this.campImage = camp.firstCampImage();
                this.campRating = camp.formatTotalRating();
            }
        }

    }

    // 전우진 240109
    // 캠핑장 상세 정보 페이지
    @Data
    public static class CampDetailDTO {
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

        private List<String> imageUrls; // 캠핑장 이미지 URL 리스트

        public CampDetailDTO(Camp camp, List<String> imageUrls) {
            this.id = camp.getId();
            this.campName = camp.getCampName();
            this.campAddress = camp.getCampAddress();
            this.campCallNumber = camp.getCampCallNumber();
            this.campWebsite = camp.getCampWebsite();
            this.campRefundPolicy = camp.getCampRefundPolicy();
            this.campWater = camp.isCampWater();
            this.campGarbageBag = camp.isCampGarbageBag();
            this.holiday = camp.getHoliday();
            this.campCheckIn = camp.getCampCheckIn();
            this.campCheckOut = camp.getCampCheckOut();
            this.campFieldImage = camp.getCampFieldImage();
            this.imageUrls = imageUrls;
        }
    }
    // 전우진 리뷰 리스트 페이지
    // 리뷰 평점 CampRatingDTO
    @Data
    public static class CampRatingDTO {
        // private Integer id;
        // private Integer campId;
        // private Integer campReviewId;
        private double total; //총 평점
        private double cleanliness;
        private double managementness;
        private double friendliness;
           
        public CampRatingDTO(CampRating campRating) {
        // public CampRatingDTO(Camp camp, User user, CampReview campReview, CampRating campRating) {
            // this.id = campRating.getId();
            // this.campId = camp.getId();
            // this.campReviewId = campReview.getId();
            this.total = campRating.total();
            this.cleanliness = campRating.getCleanliness();
            this.managementness = campRating.getManagementness();
            this.friendliness = campRating.getFriendliness();
        }

        public CampRatingDTO(double total, double cleanlinessAvg, double managementnessAvg, double friendlinessAvg) {
        }
    }
    
    // 리뷰 리스트 CampReviewListDTO
    @Data
    public static class CampReviewListDTO {
        private Integer id;
        private Integer userId;
        private Integer campId;
        private Integer orderId;
        private String userImage;
        private String nickName;
        private Timestamp createdAt;
        // total 필드의 타입이 double인 경우에는 적절하지 않을 수 있습니다. 
        // JSON으로 변환할 때 다시 전체 소수점이 나올 수 있기 때문입니다. 
        // JSON 변환 시 소수점을 제어하려면, 서버 측에서 처리하기보다는 JSON 직렬화 단계에서 처리하는 것이 더 나을 수 있습니다. 
        // 이를 위해 Jackson이 제공하는 @JsonFormat 어노테이션을 사용할 수 있습니다:
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private double total; //총 평점
        private String content;
        
        @Builder
        public CampReviewListDTO(Integer id, Integer userId, Integer campId, Integer orderId, String userImage,
                String nickName, Timestamp createdAt, double total, String content) {
            this.id = id;
            this.userId = userId;
            this.campId = campId;
            this.orderId = orderId;
            this.userImage = userImage;
            this.nickName = nickName;
            this.createdAt = createdAt;
            this.total = total;
            this.content = content;
        } 
        // public CampReviewListDTO(Camp camp, User user, Order order,
        //  CampReview campReview, CampRating campRating) {
        //     this.id = campReview.getId();
        //     this.userId = user.getId();
        //     this.campId = camp.getId();
        //     this.orderId = order.getId();
        //     this.userImage = user.getUserImage();
        //     this.nickName = user.getNickname();
        //     this.createdAt = campReview.getCreatedAt();
        //     this.total = campRating.total();
        //     this.content = campReview.getContent();
        // }

    }
    
    // 리뷰 작성 CampReviewDTO
    @Data
    public static class CampReviewDTO {
        private Integer id;
        private Integer userId;
        private Integer campId;
        private Integer orderId;
        private String campName;
        private String campAddress;
        private double cleanliness;
        private double managementness;
        private double friendliness;
        private String content;
        private String reviewImage;

        public CampReviewDTO(Camp camp, User user, Order order,
         CampReview campReview, CampRating campRating) {
            this.id = campReview.getId();
            this.userId = user.getId();
            this.campId = camp.getId();
            this.orderId = order.getId();
            this.campName = camp.getCampName();
            this.campAddress = camp.getCampAddress();
            this.cleanliness = campRating.getCleanliness();
            this.managementness = campRating.getManagementness();
            this.friendliness = campRating.getFriendliness();
            this.content = campReview.getContent();
            this.reviewImage = campReview.getReviewImage();
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

    final static String DATEFORMAT1 = "yyyy년 MM월 dd일";
    // 승신님 충돌 났길래 어떤걸 날려야 할지 몰라서 일단 주석처리 해뒀어요 -우진
    // final static String DATEFORMAT2 = "MM월 dd일";

    // @Data
    // public static class MyCampListDTO {
    // private List<MyCampDTO> myCampDTOs;

    // public MyCampListDTO(List<CampReview> campReviews, Integer year) {
    // this.myCampDTOs = campReviews.stream()
    // .filter(campReview ->
    // campReview.getOrder().getCheckInDate().toLocalDateTime().getYear() == year)
    // .sorted(Comparator.comparing(campReview -> {
    // Order order = campReview.getOrder();
    // return order.getCheckInDate();
    // }))
    // .map(campReview -> new MyCampDTO(campReview)).collect(Collectors.toList());
    // }

    // @Data
    // public class MyCampDTO {
    // private String totalRating;
    // private String checkInDate;
    // private String checkOutDate;
    // private String campAddress;
    // private String campName;
    // private String reviewImage;

    // public MyCampDTO(CampReview campReview) {
    // Order order = campReview.getOrder();
    // Camp camp = campReview.getCamp();
    // this.totalRating =
    // String.valueOf(Math.round(campReview.getCampRating().total()));
    // this.checkInDate = TimestampUtils.timeStampToDate(order.getCheckInDate(),
    // DATEFORMAT1);
    // Boolean yearCheck = order.getCheckInDate().toLocalDateTime().getYear() ==
    // order.getCheckOutDate()
    // .toLocalDateTime().getYear();
    // String dateFormat = yearCheck ? DATEFORMAT2 : DATEFORMAT1;
    // this.checkOutDate = TimestampUtils.timeStampToDate(order.getCheckOutDate(),
    // dateFormat);
    // this.campAddress = camp.getCampAddress();
    // this.campName = camp.getCampName();
    // this.reviewImage = campReview.getReviewImage();
    // }

    // }

    // }

    final static String DATEFORMAT2 = "MM월 dd일";

    // 내 캠핑장 연도별 DTO
    @Data
    public static class MyCampListDTO {
        private List<MyCampDTO> myCampDTOs;

        public MyCampListDTO(List<CampReview> campReviews, Integer year) {
            this.myCampDTOs = campReviews.stream()
                    .filter(campReview -> (year == null)
                            || campReview.getOrder().getCheckInDate().toLocalDateTime().getYear() == year)
                    .sorted(Comparator.comparing(campReview -> {
                        Order order = campReview.getOrder();
                        return order.getCheckInDate();
                    }))
                    .map(campReview -> new MyCampDTO(campReview)).collect(Collectors.toList());
        }

        @Data
        public class MyCampDTO {
            private String totalRating;
            private String checkInDate;
            private String checkOutDate;
            private String campAddress;
            private String campName;
            private String reviewImage;

            public MyCampDTO(CampReview campReview) {
                Order order = campReview.getOrder();
                Camp camp = campReview.getCamp();
                this.totalRating = String.valueOf(Math.round(campReview.getCampRating().total()));
                this.checkInDate = TimestampUtils.timeStampToDate(order.getCheckInDate(), DATEFORMAT1);
                Boolean yearCheck = order.getCheckInDate().toLocalDateTime().getYear() == order.getCheckOutDate()
                        .toLocalDateTime().getYear();
                String dateFormat = yearCheck ? DATEFORMAT2 : DATEFORMAT1;
                this.checkOutDate = TimestampUtils.timeStampToDate(order.getCheckOutDate(), dateFormat);
                this.campAddress = camp.getCampAddress();
                this.campName = camp.getCampName();
                this.reviewImage = campReview.getReviewImage();
            }

        }
    }

    // 캠프 구역 불러오기
    @Data
    public static class CampFieldListDTO {
        private CampInfoDTO campInfoDTO;
        private String checkInDate;
        private String checkOutDate;
        private Integer nights;
        private List<CampFieldDTO> campFieldDTOs;

        public CampFieldListDTO(List<CampField> campFields, Camp camp, String checkInDate, String checkOutDate) {
            this.campInfoDTO = getCampInfo(camp, campFields); // 캠핑장 정보 불러오기
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
            Period period = Period.between(LocalDate.parse(checkInDate), LocalDate.parse(checkOutDate));
            this.nights = period.getDays();
            this.campFieldDTOs = campFields.stream().map(campField -> new CampFieldDTO(campField, nights))
                    .collect(Collectors.toList());
        }

        @Data
        public static class CampFieldDTO {
            private String fieldName; // 캠프 구역
            private Integer nights; // 숙박일수
            private String totalPrice; // 총 금액

            public CampFieldDTO(CampField campField, Integer nights) {
                this.fieldName = campField.getFieldName();
                this.nights = nights;
                this.totalPrice = priceFormat(Integer.parseInt(campField.getPrice()) * nights);
            }
        }
    }

    // 캠프 상단 정보
    @Data
    public static class CampInfoDTO {
        private String campName;
        private String campAddress;
        private String minPrice;
        private String maxPrice;
        private Boolean isOpen; // 운영 상태
        private String campImage;
        private String campFieldImage;
    }

    // 캠프 정보(상단) 가져오는 메서드
    public static CampInfoDTO getCampInfo(Camp camp, List<CampField> campFields) {
        CampInfoDTO campInfo = new CampInfoDTO();
        campInfo.setCampName(camp.getCampName());
        campInfo.setCampAddress(camp.getCampAddress());
        // 최저 금액과 최고 금액 - campFields의 가격들 중 최저 가격과 최고 가격
        // 최소 가격 찾기
        Integer integerMinprice = campFields.stream()
                .map(CampField::getPrice)
                .map(Integer::parseInt)
                .min(Comparator.naturalOrder())
                .orElseThrow();
        campInfo.setMinPrice(priceFormat(integerMinprice));
        // 최대 가격 찾기
        Integer integerMaxPrice = campFields.stream()
                .map(CampField::getPrice)
                .map(Integer::parseInt)
                .max(Comparator.naturalOrder())
                .orElseThrow();
        campInfo.setMaxPrice(priceFormat(integerMaxPrice));
        // 운영 상태 = 운영 시간 내에 해당하고 휴일이 아닐 것(미완성)
        campInfo.setIsOpen(true);
        campInfo.setCampImage(camp.firstCampImage());
        campInfo.setCampFieldImage(camp.getCampFieldImage());
        return campInfo;
    }

    // 금액 포맷팅
    public static String priceFormat(Integer price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(price);
    }

}
