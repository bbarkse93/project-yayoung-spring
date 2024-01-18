package com.example.team_project.order._dto;



import java.util.List;
import java.util.stream.Collectors;

import com.example.team_project._core.utils.TimestampUtils;
import com.example.team_project.camp.Camp;
import com.example.team_project.order.Order;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;


@Data
public class OrderRespDTO {
	final static String DATEFORMAT1 = "yyyy년 MM월 dd일";
	final static String DATEFORMAT2 = "MM월 dd일";
	final static String DATEFORMAT3 = "MM/dd(EE)";
	
	@Getter
	@ToString
	public static class ImminentOrderDetailDTO {
		private String campName;
		private String checkInDate;
		private String checkInDDay;
		
		public ImminentOrderDetailDTO(Order order) {
			this.campName = order.getCampField().getCamp().getCampName();
			this.checkInDate = TimestampUtils.timeStampToDate(order.getCheckInDate(), DATEFORMAT3);
			this.checkInDDay = TimestampUtils.formatDDay(order.getCheckInDate());
		}
	}
	
	@Getter
	@ToString
	public static class CampScheduleListDTO{
		private List<CampScheduleDTO> campScheduleDTOs; 
		
		public CampScheduleListDTO(List<Order> orders) {
			this.campScheduleDTOs = orders.stream()
					.map(order -> new CampScheduleDTO(order)).collect(Collectors.toList());
		}
		
		@Getter
		@ToString
		public class CampScheduleDTO{
			private Integer orderId;
			private Integer campId;
			private String campName;
			private String campAddress;
			private String checkInDate;
			private String checkInDDay;
			private String campField;
			
			public CampScheduleDTO(Order order) {
				Camp camp = order.getCampField().getCamp();
				this.orderId = order.getId();
				this.campId = camp.getId();
				this.campName = camp.getCampName();
				this.campAddress = camp.getCampAddress();
				this.checkInDate = TimestampUtils.timeStampToDate(order.getCheckInDate(), DATEFORMAT2);
				this.checkInDDay = TimestampUtils.formatDDay(order.getCheckInDate());
				this.campField = order.getCampField().getFieldName();
			}
		}
	}

	@Getter
	@ToString
	public static class PaymentWriteDTO{
		private String campFieldImage;
		public PaymentWriteDTO(String campFieldImage) {
			this.campFieldImage = campFieldImage;
			
		}	
	}
	
}
