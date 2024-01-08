package com.example.team_project.camp._dto;

import com.example.team_project._core.utils.TimestampUtils;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.camp_bookmark.CampBookmark;
import com.example.team_project.camp.camp_image.CampImage;
import com.example.team_project.camp.camp_rating.CampRating;
import com.example.team_project.camp.camp_review.CampReview;
import com.example.team_project.order.Order;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
//import net.bytebuddy.build.HashCodeAndEqualsPlugin.Sorted;

import java.util.Comparator;
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
    
    final static String DATEFORMAT1 = "yyyy년 MM월 dd일";
	final static String DATEFORMAT2 = "MM월 dd일";
    
	@Data
	public static class MyCampListDTO {
		private List<MyCampDTO> myCampDTOs;
		public MyCampListDTO(List<CampReview> campReviews, Integer year) {
			this.myCampDTOs = campReviews.stream()
					.filter(campReview -> campReview.getOrder().getCheckInDate().toLocalDateTime().getYear() == year)
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
    
    
}
