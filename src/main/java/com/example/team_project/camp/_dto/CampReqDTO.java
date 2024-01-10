package com.example.team_project.camp._dto;

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

	
}
