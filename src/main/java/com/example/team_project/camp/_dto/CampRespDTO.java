package com.example.team_project.camp._dto;

import com.example.team_project._core.errors.exception.Exception500;
import com.example.team_project._core.utils.TimestampUtils;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.camp_bookmark.CampBookmark;
import com.example.team_project.camp.camp_image.CampImage;
import com.example.team_project.camp.camp_rating.CampRating;
import com.example.team_project.camp.camp_review.CampReview;
import com.example.team_project.camp_field.CampField;
import com.example.team_project.option_management.OptionManagement;
import com.example.team_project.order.Order;
import com.example.team_project.order._dto.OrderReqDTO;
import lombok.Data;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class CampRespDTO {

    // 전우진 240108
    // 캠핑장 목록 페이지
    @Data
    public static class CampListDTO {
        private List<CampDTO> campDTO;

        public CampListDTO(List<Camp> campList, CampReqDTO.CampListDTO requestDTO) {
            // 환경 필터 적용
        	if (requestDTO.getOptionNames() != null) {
        	    campList = campList.stream()
        	            .filter(camp -> requestDTO.getOptionNames()
        	                    .stream()
        	                    .allMatch(optionName ->
        	                            camp.getOptionManagementList()
        	                                    .stream()
        	                                    .anyMatch(om -> optionName.equals(om.getOption().getOptionName()))
        	                    )
        	            )
        	            .collect(Collectors.toList());
        	}
        	
//        	// 지역 필터 적용
//        	if(requestDTO.getRegionNames() != null) {
//        		campList =	campList.stream()
//        			  .filter(camp -> {
//        	                for (String regionName : requestDTO.getRegionNames()) {
//        	                    if (regionName.equals(camp.getCampAddress().split(" ")[0])) {
//        	                        return true;
//        	                    }
//        	                }
//        	                return false;
//        	            })
//        	            .collect(Collectors.toList());
//        	}
        	this.campDTO = campList.stream().map(c -> new CampDTO(c)).collect(Collectors.toList());
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

    @Data
    public static class CampDetailDTO {
        private CampDTO campInfo;
        private RatingAverages campRating;
        private long reviewCount;
        private List<CampImageDTO> images;
        private Map<String, List<OptionManagementDTO>> options;

        public CampDetailDTO(Camp camp, long reviewCount) {
            this.campInfo = new CampDTO(camp);
            this.campRating = ratingAverages(camp.getCampRatingList());
            this.images = camp.getCampImageList().stream().map(c -> new CampImageDTO(c)).collect(Collectors.toList());
            this.options = camp.getOptionManagementList().stream().map(c -> new OptionManagementDTO(c)).collect(Collectors.groupingBy(OptionManagementDTO::getCategoryName));
            this.reviewCount = reviewCount;
        }

        @Data
        public static class CampDTO {
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
            private CampFieldDTO campPrice;
            private String totalRating;

            public CampDTO(Camp camp) {
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
                this.campPrice = new CampFieldDTO(camp.getCampFieldList());
                this.totalRating = camp.formatTotalRating();
            }
        }

        @Data
        public static class CampFieldDTO {
            private Integer minPrice;
            private Integer maxPrice;

            public CampFieldDTO(List<CampField> campField) {
                this.minPrice = campField.stream()
                        .map(CampField::getPrice)
                        .min(Comparator.naturalOrder())
                        .orElseThrow();
                this.maxPrice = campField.stream()
                        .map(CampField::getPrice)
                        .max(Comparator.naturalOrder())
                        .orElseThrow();
            }
        }

        @Data
        public static class CampRatingDTO {
            private Integer campRatingId;
            private double cleanliness;
            private double managementness;
            private double friendliness;

            public CampRatingDTO(CampRating campRating) {
                this.campRatingId = campRating.getId();
                this.cleanliness = campRating.getCleanliness();
                this.managementness = campRating.getManagementness();
                this.friendliness = campRating.getFriendliness();
            }
        }

        @Data
        public static class RatingAverages {
            private double cleanlinessAverage;
            private double managementnessAverage;
            private double friendlinessAverage;

            public RatingAverages(double cleanlinessAverage, double managementnessAverage, double friendlinessAverage) {
                this.cleanlinessAverage = cleanlinessAverage;
                this.managementnessAverage = managementnessAverage;
                this.friendlinessAverage = friendlinessAverage;
            }

        }

        @Data
        public static class OptionManagementDTO {
            private Integer optionId;
            private String optionName;
            private Integer categoryId;
            private String categoryName;

            public OptionManagementDTO(OptionManagement optionManagement) {
                this.optionId = optionManagement.getOption().getId();
                this.optionName = optionManagement.getOption().getOptionName();
                this.categoryId = optionManagement.getOption().getOptionCategory().getId();
                this.categoryName = optionManagement.getOption().getOptionCategory().getCategoryName();
            }
        }

        private RatingAverages ratingAverages(List<CampRating> ratings) {
            double cleanlinessAverage = ratings.stream().mapToDouble(CampRating::getCleanliness).average().orElse(0);
            double managementnessAverage = ratings.stream().mapToDouble(CampRating::getManagementness).average().orElse(0);
            double friendlinessAverage = ratings.stream().mapToDouble(CampRating::getFriendliness).average().orElse(0);
            return new RatingAverages(cleanlinessAverage, managementnessAverage, friendlinessAverage);
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
    final static String DATEFORMAT2 = "MM월 dd일";
    // 내 캠핑장 연도별 DTO
    @Data
    public static class MyCampListDTO {
        private List<MyCampDTO> myCampDTOs;
        public MyCampListDTO(List<CampReview> campReviews, Integer year) {
            this.myCampDTOs = campReviews.stream()
                    .filter(campReview -> ( year == null ) || campReview.getOrder().getCheckInDate().toLocalDateTime().getYear() == year)
                    .sorted(Comparator.comparing(campReview -> {
                        Order order = campReview.getOrder();
                        return order.getCheckInDate();
                    }))
                    .map(campReview -> new MyCampDTO(campReview)).collect(Collectors.toList());
        }
        @Data
        public class MyCampDTO{
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
                this.checkInDate = TimestampUtils.timeStampToDate
                        (order.getCheckInDate(), DATEFORMAT1);
                Boolean yearCheck = order.getCheckInDate().toLocalDateTime().getYear()
                        == order.getCheckOutDate().toLocalDateTime().getYear();
                String dateFormat = yearCheck ? DATEFORMAT2 : DATEFORMAT1;
                this.checkOutDate = TimestampUtils.timeStampToDate
                        (order.getCheckOutDate(), dateFormat);
                this.campAddress = camp.getCampAddress();
                this.campName = camp.getCampName();
                this.reviewImage = campReview.getReviewImage();
            }
        }
    }

    // 캠프 구역 불러오기
    @Data
    public static class CampFieldListDTO{
        private CampInfoDTO campInfoDTO;
        private Integer campId;
        private String campFieldImage;
        private List<CampFieldDTO> campFieldDTOs;
        private List<ReservedCampFieldDTO> reservedCampFieldDTOs;
        public CampFieldListDTO(List<CampField> campFields, Camp camp, List<Order> orders, OrderReqDTO.CampFieldListDTO requestDTO) {
            this.campInfoDTO = getCampInfo(camp, campFields); // 캠핑장 정보 불러오기
            this.campId = requestDTO.getCampId();
            this.campFieldImage = camp.getCampFieldImage();
            this.reservedCampFieldDTOs = orders.stream()
                    .filter(order -> order != null && order.getCampField().getCamp().getId() == requestDTO.getCampId())
                    .map(order -> new ReservedCampFieldDTO(order))
                    .collect(Collectors.toList());
            this.campFieldDTOs = campFields.stream()
                    .map(campField -> new CampFieldDTO(campField))
                    .collect(Collectors.toList());
        }
        @Data
        public static class CampFieldDTO{
            private String fieldName; // 캠프 구역
            private String price;	// 금액
            public CampFieldDTO(CampField campField) {
                this.fieldName = campField.getFieldName();
                this.price = priceFormat(campField.getPrice());
            }
        }
        @Data
        public static class ReservedCampFieldDTO{
            private String fieldName;
            private String checkInDate;
            private String checkOutDate;
            public ReservedCampFieldDTO(Order order) {
                this.fieldName = order.getCampField().getFieldName();
                this.checkInDate = String.valueOf(order.getCheckInDate()).split(" ")[0];
                this.checkOutDate = String.valueOf(order.getCheckOutDate()).split(" ")[0];
            }

        }

    }

    //캠프 상단 정보
    @Data
    public static class CampInfoDTO{
        private String campName;
        private String campAddress;
        private String minPrice;
        private String maxPrice;
        private Boolean isOpen; // 운영 상태
        private String campImage;
    }

    // 캠프 정보(상단) 가져오는 메서드
    public static CampInfoDTO getCampInfo(Camp camp, List<CampField> campFields) {
        CampInfoDTO campInfo = new CampInfoDTO();
        campInfo.setCampName(camp.getCampName());
        campInfo.setCampAddress(camp.getCampAddress());
        // 최저 금액과 최고 금액 - campFields의 가격들 중 최저 가격과 최고 가격
        // 최소 가격 찾기
        Integer integerMinPrice = campFields.stream()
                .map(CampField::getPrice)
                .min(Comparator.naturalOrder())
                .orElseThrow();
        campInfo.setMinPrice(priceFormat(integerMinPrice));

        // 최대 가격 찾기
        Integer integerMaxPrice = campFields.stream()
                .map(CampField::getPrice)
                .max(Comparator.naturalOrder())
                .orElseThrow();
        campInfo.setMaxPrice(priceFormat(integerMaxPrice));

        Timestamp now = TimestampUtils.findCurrnetTime();
        String today = String.valueOf(now).substring(0, 10);
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date checkInDate; // 운영 시작 시간
        Date checkOutDate; // 운영 종료 시간
        try {
            checkInDate = timeFormat.parse(today + " " + camp.getCampCheckIn());
            checkOutDate = timeFormat.parse(today + " " + camp.getCampCheckOut());
            campInfo.setIsOpen(now.compareTo(checkInDate) >= 0 && now.compareTo(checkOutDate) < 0);
        } catch (ParseException e) {
            throw new Exception500("서버 에러가 발생했습니다");
        }
        campInfo.setIsOpen(true);
        campInfo.setCampImage(camp.firstCampImage());
        return campInfo;
    }

    // 금액 포맷팅
    public static String priceFormat(Integer price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(price);
    }

    @Data
    public static class SearchCampDTO {
        List<CampDTO> campList;

        public SearchCampDTO(List<Camp> campList) {
            this.campList = campList.stream().map(c -> new CampDTO(c)).collect(Collectors.toList());
        }
        @Data
        public static class CampDTO {
            private Integer id;
            private String campName;
            private String campAddress;

            public CampDTO(Camp camp) {
                this.id = camp.getId();
                this.campName = camp.getCampName();
                this.campAddress = camp.getCampAddress();
            }
        }

    }
}