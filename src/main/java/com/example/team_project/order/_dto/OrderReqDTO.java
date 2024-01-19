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
		private Integer totalPrice;
		@NotEmpty
		private String orderNumber;
	}
	
	@Data
	public static class RefundInfoDTO{
		@NotNull
		private Integer orderId;
		@NotNull
		private Integer campId;
	}
	
	
}
