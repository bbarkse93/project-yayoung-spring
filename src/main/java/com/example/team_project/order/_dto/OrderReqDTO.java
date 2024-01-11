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
}
