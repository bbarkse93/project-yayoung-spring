package com.example.team_project.camp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.team_project.MyWithRestDoc;

@SpringBootTest
public class CampRestController_test extends MyWithRestDoc{
	@Test
	public void myCampList_test() throws Exception  {
		
		// given
		
		// when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders
				.get("/camp/myCamp")
				
				);
		
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println(responseBody);
		// then
		
		resultActions
		.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[0].totalRating").value(4))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[0].checkInDate").value("2024년 01월 15일"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[0].checkOutDate").value("01월 10일"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[0].campAddress").value("강원도 춘천시 남면 가옹개길 52-9"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[0].campName").value("(주)아웃오브파크"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[0].reviewImage").value("camp_image/camp1-1.jpg"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[1].totalRating").value(5))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[1].checkInDate").value("2024년 01월 15일"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[1].checkOutDate").value("01월 11일"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[1].campAddress").value("강원도 춘천시 남면 가옹개길 52-9"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[1].campName").value("(주)아웃오브파크"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[1].reviewImage").value("camp_image/camp1-1.jpg"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
        .andDo(MockMvcResultHandlers.print())
        .andDo(document);
	}
}
