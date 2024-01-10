package com.example.team_project.camp_field;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.team_project.MyWithRestDoc;
import com.example.team_project.camp_field._dto.CampFieldReqDTO;

@SpringBootTest
public class CampFieldRestController_test extends MyWithRestDoc {
	
	
	@Test
	public void campFieldList_test() throws Exception {
		
		//given
		CampFieldReqDTO.CampFieldListDTO requestDTO = new CampFieldReqDTO.CampFieldListDTO();
		requestDTO.setCampId(1);
		requestDTO.setCheckInDate("2024-01-10");
		requestDTO.setCheckOutDate("2024-01-15");
		
		//when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders
						.get("/camp-field/list")
						.param("campId", String.valueOf(requestDTO.getCampId()))
						.param("checkInDate", requestDTO.getCheckInDate())
						.param("checkOutDate", requestDTO.getCheckOutDate())
				);
		String responsebody = resultActions
				.andReturn()
				.getResponse()
				.getContentAsString();
		
		System.out.println("ResultAction : " + responsebody);
		
		//then
		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[0].fieldName").value("캠핑사이트-1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[0].nights").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[0].totalPrice").value("250,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[1].fieldName").value("캠핑사이트-2"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[1].nights").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[1].totalPrice").value("250,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[2].fieldName").value("캠핑사이트-3"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[2].nights").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[2].totalPrice").value("250,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[3].fieldName").value("캠핑사이트-4"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[3].nights").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[3].totalPrice").value("250,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[4].fieldName").value("캠핑사이트-5"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[4].nights").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[4].totalPrice").value("250,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[5].fieldName").value("캠핑사이트-6"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[5].nights").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[5].totalPrice").value("250,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[6].fieldName").value("캠핑카 13-1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[6].nights").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[6].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[7].fieldName").value("캠핑카 17-1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[7].nights").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[7].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[8].fieldName").value("캠핑카 20-1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[8].nights").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[8].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[9].fieldName").value("캠핑카 20-2"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[9].nights").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[9].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[10].fieldName").value("캠핑카 20-3"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[10].nights").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[10].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[11].fieldName").value("캠핑카 20-4"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[11].nights").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[11].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[12].fieldName").value("캠핑카 26-1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[12].nights").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[12].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[13].fieldName").value("캠핑카 26-2"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[13].nights").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[13].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
				.andDo(MockMvcResultHandlers.print())
				.andDo(document);
	}
	
}
