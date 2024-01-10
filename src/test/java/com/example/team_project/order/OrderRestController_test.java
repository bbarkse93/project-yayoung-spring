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
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.checkInDate").value("01/10(월)"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.checkInDDay").value("D-0"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
	            .andDo(MockMvcResultHandlers.print())
	            .andDo(document);
	}
	
	public void campScheduleList_test() {
		// given
		
		// when
		
		// then
	}
	
	
	
	
}
