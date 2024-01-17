package com.example.team_project.admin._dto;

import com.example.team_project.board.Board;
import com.example.team_project.board._dto.BoardRespDTO;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.camp_image.CampImage;
import com.example.team_project.camp.camp_rating.CampRating;
import com.example.team_project.camp.camp_review.CampReview;
import com.example.team_project.camp_field.CampField;
import com.example.team_project.notice.Notice;
import com.example.team_project.option.Option;
import com.example.team_project.option_management.OptionManagement;
import com.example.team_project.order.Order;
import com.example.team_project.user.User;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

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
            this.orderCount = camp.getCampFieldList().stream().mapToInt(CampField::orderCount).sum();
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

    // 유저 DTO
    @Data
    public static class UserDTO{
        private Integer userId;
        private String username;
        private String nickname;
        private boolean isWithDraw;
        private String createdAt;

        public UserDTO(User user) {
            this.userId = user.getId();
            this.username = user.getUsername();
            this.nickname = user.getNickname();
            this.isWithDraw = user.isWithdraw();
            this.createdAt = user.formatCreateAt();
        }
    }

    // 유저 상세 DTO
    @Data
    public static class UserDetailDTO{
        private Integer userId;
        private String nickname;
        private String userImage;
        private List<OrderDTO> orderDTOList;
        private List<ReviewDTO> reviewDTOList;

        public UserDetailDTO(User user, List<Order> orderList, List<CampReview> campReviewList) {
            this.userId = user.getId();
            this.nickname = user.getNickname();
            this.userImage = user.getUserImage();
            this.orderDTOList = orderList.stream().map(OrderDTO::new).collect(Collectors.toList());
            this.reviewDTOList = campReviewList.stream().map(ReviewDTO::new).collect(Collectors.toList());
        }

        @Data
        public static class OrderDTO {
            private Integer orderId;
            private String campName;
            private String checkInDate;
            private String checkOutDate;
            private String campField;
            private String price;
            private String orderCreatedAt;

            public OrderDTO(Order order) {
                this.orderId = order.getId();
                this.campName = order.getCampField().getCamp().getCampName();
                this.checkInDate = order.formatCheckInDate();
                this.checkOutDate = order.formatCheckOutDate();
                this.campField = order.getCampField().getFieldName();
                this.price = order.getCampField().formatPrice();
                this.orderCreatedAt = order.formatCreatedAt();
            }
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

    // faq 목록 DTO
    @Data
    public static class FaqDTOList {
        private List<PaymentDTO> paymentDTOList;
        private List<UserDTO> userDTOList;

        public FaqDTOList(List<Board> boardList) {
            this.paymentDTOList = boardList.stream().filter(board -> board.getBoardCategory().getId() == 1).map(PaymentDTO::new).collect(Collectors.toList());
            this.userDTOList = boardList.stream().filter(board -> board.getBoardCategory().getId() == 2).map(UserDTO::new).collect(Collectors.toList());
        }

        @Data
        public static class PaymentDTO {
            private Integer faqId;
            private String title;
            private String content;
            private String createdAt;

            public PaymentDTO(Board board) {
                this.faqId = board.getId();
                this.title = board.getTitle();
                this.content = board.getContent();
                this.createdAt = board.formatCreatedAt();
            }
        }

        @Data
        public static class UserDTO {
            private Integer faqId;
            private String title;
            private String content;
            private String createdAt;

            public UserDTO(Board board) {
                this.faqId = board.getId();
                this.title = board.getTitle();
                this.content = board.getContent();
                this.createdAt = board.formatCreatedAt();
            }
        }
    }

    // faq 상세보기 DTO
    @Data
    public static class FaqDetailDTO {
        private Integer faqId;
        private String title;
        private String content;
        private Integer categoryId;
        private String categoryName;
        private String createdAt;

        public FaqDetailDTO(Board board) {
            this.faqId = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.categoryId = board.getBoardCategory().getId();
            this.categoryName = board.getBoardCategory().getTitle();
            this.createdAt = board.formatCreatedAt();
        }
    }

    // notice 목록 DTO
    @Data
    public static class NoticeDTO {
        private Integer noticeId;
        private String title;
        private String content;
        private String createdAt;

        public NoticeDTO(Notice notice) {
            this.noticeId = notice.getId();
            this.title = notice.getTitle();
            this.content = notice.getContent();
            this.createdAt = notice.formatCreatedAt();
        }
    }

    // notice 상세보기 DTO
    @Data
    public static class NoticeDetailDTO {
        private Integer noticeId;
        private String title;
        private String content;
        private String createdAt;

        public NoticeDetailDTO(Notice notice) {
            this.noticeId = notice.getId();
            this.title = notice.getTitle();
            this.content = notice.getContent();
            this.createdAt = notice.formatCreatedAt();
        }
    }

    // 캠핑장 옵션 목록 DTO
    @Data
    public static class OptionDTO {
        private Integer optionId;
        private String optionName;
        private Integer optionCategoryId;

        public OptionDTO(Option option) {
            this.optionId = option.getId();
            this.optionName = option.getOptionName();
            this.optionCategoryId = option.getOptionCategory().getId();
        }
    }

    // camp 상세 DTO
    @Data
    public static class CampDetailDTO {
        private Integer campId;
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
        private List<CampImageDTO> campPhotoList;
        private List<CampOptionDTO> campOptionDTOList;
        private String campFieldImage;
        private List<campFieldDTO> campFieldDTOList;

        public CampDetailDTO(Camp camp) {
            this.campId = camp.getId();
            this.campName = camp.getCampName();
            this.campAddress = camp.getCampAddress();
            this.campCallNumber = camp.getCampCallNumber();
            this.campWebsite = camp.getCampWebsite();
            this.holiday = camp.getHoliday();
            this.campCheckIn = camp.getCampCheckIn();
            this.campCheckOut = camp.getCampCheckOut();
            this.campWater = camp.isCampWater();
            this.campGarbageBag = camp.isCampGarbageBag();
            this.campRefundPolicy = camp.getCampRefundPolicy();
            this.campPhotoList = camp.getCampImageList().stream().map(CampImageDTO::new).collect(Collectors.toList());
            this.campOptionDTOList = camp.getOptionManagementList().stream().map(CampOptionDTO::new).collect(Collectors.toList());
            this.campFieldImage = camp.getCampFieldImage();
            this.campFieldDTOList = camp.getCampFieldList().stream().map(AdminRespDTO.CampDetailDTO.campFieldDTO::new).collect(Collectors.toList());
        }

        @Data
        public static class CampImageDTO {
            private Integer campImageId;
            private String campImage;

            public CampImageDTO(CampImage campImage) {
                this.campImageId = campImage.getId();
                this.campImage = campImage.getCampImage();
            }
        }

        @Data
        public static class CampOptionDTO{
            private Integer selectOptionId;

            public CampOptionDTO(OptionManagement optionManagement) {
                this.selectOptionId = optionManagement.getOption().getId();
            }
        }

        @Data
        public static class campFieldDTO{
            private Integer fieldId;
            private String fieldName;
            private Integer price;

            public campFieldDTO(CampField campField) {
                this.fieldId = campField.getId();
                this.fieldName = campField.getFieldName();
                this.price = campField.getPrice();
            }
        }
    }

    // refund 목록 DTO
    @Data
    public static class RefundDTO{
        private Integer orderId;
        private String nickName;
        private String campName;
        private String campField;
        private String price;
        private String refundAt;

        public RefundDTO(Order order) {
            this.orderId = order.getId();
            this.nickName = order.getUser().getNickname();
            this.campName = order.getCampField().getCamp().getCampName();
            this.campField = order.getCampField().getFieldName();
            this.price = order.getCampField().formatPrice();
            this.refundAt = order.formatCreatedAt();
        }
    }

}