package com.example.team_project.camp_field._dto;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import com.example.team_project.camp_field.CampField;

import lombok.Data;

@Data
public class CampFieldRespDTO {
	
	@Data
	public static class CampFieldListDTO{
		private List<CampFieldDTO> campFieldDTOs;
		public CampFieldListDTO(List<CampField> campFields, String checkInDate, String checkOutDate) {
			Period period = Period.between(LocalDate.parse(checkInDate), LocalDate.parse(checkOutDate));
			Integer nights = period.getDays();
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
	
	// 금액 포맷팅
	public static String priceFormat(Integer price) {
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		return decimalFormat.format(price);  
	}
	
	
}
