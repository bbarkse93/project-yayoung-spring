package com.example.team_project.order._dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderReqDTO {
	@Data
	public static class CampFieldListDTO{
		private Integer campId; // 캠프장 아이디
	}
		
	@Data
	public static class PaymentWriteDTO{
		@NotNull
		private Integer campId;
		@NotEmpty
		private String  checkIn;
		@NotEmpty
		private String  checkOut;
		@NotEmpty
		private String  fieldName;
		@NotNull
		private Integer  totalPrice;
	}
	
	@Data
	public static class OrderDeleteDTO{
		@NotNull
		private Integer orderId;
		@NotEmpty
		private String campName;
		@NotEmpty
		private String campAddress;
		@NotEmpty
		private String checkInDate;
		@NotEmpty
		private String fieldName;
	}
	
	
}
