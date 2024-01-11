package com.example.team_project.order._dto;

import lombok.Data;

@Data
public class OrderReqDTO {
	@Data
	public static class CampFieldListDTO{
		private Integer campId; // 캠프장 아이디
		private String checkInDate; //체크인 날짜
		private String checkOutDate; //체크아웃 날짜
	}
	
	@Data
	public static class PaymentDetailDTO{
		private Integer campId; // 캠프장 아이디
		private String  campField; // 캠프 구역
		private String  checkInDate; //체크인 날짜
		private String  checkOutDate; //체크아웃 날짜
		private Integer nights; // 숙박 일수
		private String  price;  // 가격
	}
	
}
