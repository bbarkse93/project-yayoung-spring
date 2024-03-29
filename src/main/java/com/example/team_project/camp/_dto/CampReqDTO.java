package com.example.team_project.camp._dto;

import java.util.List;

import org.hibernate.procedure.spi.CallableStatementSupport;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CampReqDTO {

	@Data
	public static class MyCampListDTO {
		private Integer year;
	}

	@Data
	public static class CampBookmarkDTO {
		@NotNull
		private Integer campId;
	}
	
	@Data
	public static class CampBookmarkDeleteDTO {
		@NotNull
		private Integer campId;
	}
	

	@Data
	public static class CampListDTO {
		private List<String> optionNames; // 필터 옵션
		private List<String> regionNames; // 지역 옵션
	}

	@Data
	public static class CampReviewDTO{
		private Integer campId;
		private String content;
		private double cleanliness;
		private double managementness;
		private double friendliness;
		private Integer userId;
	}
	
	@Data
	public static class CampReviewListDTO {
		private Integer campId;
	}
	
}
