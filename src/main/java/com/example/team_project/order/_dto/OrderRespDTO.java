package com.example.team_project.order._dto;



import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import com.example.team_project.order.Order;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;


@Data
public class OrderRespDTO {
	
	@Getter
	@ToString
	public static class ImminentOrderDetailDTO {
		private String fieldName;
		private String checkInDate;
		private String checkInDDay;
		
		public ImminentOrderDetailDTO(Order order) {
			this.fieldName = order.getCampField().getFieldName();
			this.checkInDate = new SimpleDateFormat("MM/dd(EE)").format(order.getCheckInDate());
			this.checkInDDay = formatDDay(order.getCheckInDate());
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
		public class CampScheduleDTO{
			private String fieldName;
			private String campAddress;
			private String checkInDate;
			private String checkInDDay;
			
			public CampScheduleDTO(Order order) {
				this.fieldName = order.getCampField().getFieldName();
				this.campAddress = order.getCampField().getCamp().getCampAddress();
				this.checkInDate = new SimpleDateFormat("MM월 dd일").format(order.getCheckInDate());
				this.checkInDDay = formatDDay(order.getCheckInDate());
			}
			
			
			
		}
		
		
	}
	
	// 현재와 비교해 D-day를 반환하는 함수
	public static String formatDDay(Timestamp date) {
		ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");
		LocalDate currentDate = LocalDate.now(koreaZoneId);
		String remainningDays = String.valueOf(currentDate.until(date.toLocalDateTime().toLocalDate()).getDays()) ;
		return "D-"+remainningDays;
	}
	
}
