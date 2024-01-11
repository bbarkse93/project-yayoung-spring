package com.example.team_project.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.team_project.MyWithRestDoc;
import com.example.team_project.order._dto.OrderReqDTO;

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
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.checkInDate").value("01/17(수)"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.checkInDDay").value("D-6"))
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
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[0].checkInDate").value("01월 17일"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[0].checkInDDay").value("D-6"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[0].campField").value("캠핑사이트-5"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[1].campName").value("(주)아웃오브파크"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[1].campAddress").value("강원도 춘천시 남면 가옹개길 52-9"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[1].checkInDate").value("01월 20일"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[1].checkInDDay").value("D-9"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[1].campField").value("캠핑사이트-3"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[2].campName").value("(주)아웃오브파크"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[2].campAddress").value("강원도 춘천시 남면 가옹개길 52-9"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[2].checkInDate").value("02월 03일"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[2].checkInDDay").value("D-23"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs[2].campField").value("캠핑사이트-3"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
				.andDo(MockMvcResultHandlers.print())
				.andDo(document);
	}
	
	@Test
	public void campFieldList_test() throws Exception {
		
		//given
		OrderReqDTO.CampFieldListDTO requestDTO = new OrderReqDTO.CampFieldListDTO();
		requestDTO.setCampId(1);
		requestDTO.setCheckInDate("2024-01-10");
		requestDTO.setCheckOutDate("2024-01-15");
		
		//when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders
						.get("/order/field-list")
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
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.campName").value("(주)아웃오브파크"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.campAddress").value("강원도 춘천시 남면 가옹개길 52-9"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.minPrice").value("50,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.maxPrice").value("60,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.isOpen").value(true))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.campImage").value("/images/camp_image/camp1-1.jpg"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.checkInDate").value("2024-01-10"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.checkOutDate").value("2024-01-15"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.nights").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldImage").value("/images/camp_map/camp1.png"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[0].fieldName").value("캠핑사이트-1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[0].totalPrice").value("250,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[1].fieldName").value("캠핑사이트-2"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[1].totalPrice").value("250,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[2].fieldName").value("캠핑사이트-3"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[2].totalPrice").value("250,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[3].fieldName").value("캠핑사이트-4"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[3].totalPrice").value("250,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[4].fieldName").value("캠핑사이트-5"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[4].totalPrice").value("250,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[5].fieldName").value("캠핑사이트-6"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[5].totalPrice").value("250,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[6].fieldName").value("캠핑카 13-1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[6].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[7].fieldName").value("캠핑카 17-1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[7].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[8].fieldName").value("캠핑카 20-1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[8].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[9].fieldName").value("캠핑카 20-2"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[9].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[10].fieldName").value("캠핑카 20-3"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[10].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[11].fieldName").value("캠핑카 20-4"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[11].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[12].fieldName").value("캠핑카 26-1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[12].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[13].fieldName").value("캠핑카 26-2"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs[13].totalPrice").value("300,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
				.andDo(MockMvcResultHandlers.print())
				.andDo(document);
	}
	
	
}
