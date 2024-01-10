package com.example.team_project.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.team_project.MyWithRestDoc;

@SpringBootTest
public class OrderRestController_test extends MyWithRestDoc {
	
	@Test
	public void imminentOrderDetail_test() throws Exception {
		//given
		
		//when
		ResultActions resltActions = mockMvc.perform(
				MockMvcRequestBuilders
						.get("/order/imminentOrder"));
		String responsebody = resltActions
				.andReturn()
				.getResponse()
				.getContentAsString();
		
		System.out.println("ResultAction : " + responsebody);		
		
		//then
		resltActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campName").value("(주)아웃오브파크"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.checkInDate").value("01/11(목)"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.checkInDDay").value("D-1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
	            .andDo(MockMvcResultHandlers.print())
	            .andDo(document);
	}
	
	@Test
	public void campScheduleList_test() throws Exception {
		// given

		// when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders
				.get("/order/campSchedule"));
		String responsebody = resultActions
				.andReturn()
				.getResponse()
				.getContentAsString();

		System.out.println("ResultAction:" + responsebody);
		// then
		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[0].campName").value("(주)아웃오브파크"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[0].campAddress").value("강원도 춘천시 남면 가옹개길 52-9"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[0].checkInDate").value("01월 11일"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[0].checkInDDay").value("D-1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[1].campName").value("(주)아웃오브파크"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[1].campAddress").value("강원도 춘천시 남면 가옹개길 52-9"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[1].checkInDate").value("01월 17일"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[1].checkInDDay").value("D-7"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[2].campName").value("(주)아웃오브파크"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[2].campAddress").value("강원도 춘천시 남면 가옹개길 52-9"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[2].checkInDate").value("01월 20일"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[2].checkInDDay").value("D-10"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[3].campName").value("(주)아웃오브파크"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[3].campAddress").value("강원도 춘천시 남면 가옹개길 52-9"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[3].checkInDate").value("02월 03일"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[3].checkInDDay").value("D-24"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
				.andDo(MockMvcResultHandlers.print())
				.andDo(document);
	}
	
	
	
	
}
