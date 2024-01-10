package com.example.team_project.camp._dto;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.team_project._core.utils.TimestampUtils;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.camp_bookmark.CampBookmark;
import com.example.team_project.camp.camp_review.CampReview;
import com.example.team_project.camp_field.CampField;
import com.example.team_project.order.Order;

import lombok.Data;

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
			this.campFieldDTOs = campFields.stream().map(campField -> new CampFieldDTO(campField, nights)).collect(Collectors.toList());
		}
		@Data
		public static class CampFieldDTO{
			private String fieldName; // 캠프 구역
			private Integer nights; // 숙박일수
			private String totalPrice;	// 총 금액
			public CampFieldDTO(CampField campField, Integer nights) {
				this.fieldName = campField.getFieldName();
				this.nights = nights;
				this.totalPrice = priceFormat(Integer.parseInt(campField.getPrice())*nights);
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
