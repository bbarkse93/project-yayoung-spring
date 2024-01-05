package com.example.team_project.order._dto;



import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

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
		private String fieldName;
		private String campAddress;
		private String checkInDate;
		private String checkInDDay;
		
		public CampScheduleListDTO(List<Order> orders) {
			
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
