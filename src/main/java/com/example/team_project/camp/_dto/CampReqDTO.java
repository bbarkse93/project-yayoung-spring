package com.example.team_project.camp._dto;

import java.util.List;

import lombok.Data;

@Data
public class CampReqDTO {

	@Data
	public static class MyCampListDTO {
		private Integer year;
	}

	@Data
	public static class CampBookmarkDTO {
		private Integer campId;
	}

	@Data
	public static class CampListDTO{
		private List<String> optionNames; // 필터 옵션
		private List<String> regionNames; // 지역 옵션
	}
	
	
}
