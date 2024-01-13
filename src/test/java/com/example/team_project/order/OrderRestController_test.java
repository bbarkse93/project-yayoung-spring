package com.example.team_project.order;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.team_project.MyWithRestDoc;
import com.example.team_project.order._dto.OrderReqDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class OrderRestController_test extends MyWithRestDoc {

	@Test
	public void imminentOrderDetail_test() throws Exception {
		//given
		
		//when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders
						.get("/order/imminentOrder"));
		String responseBody = resultActions
				.andReturn()
				.getResponse()
				.getContentAsString();
		
		System.out.println("ResultAction : " + responseBody);		
		
		//then
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> bodyMap = om.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
		Map<String, Object> responseMap = om.convertValue(bodyMap.get("response"), new TypeReference<Map<String, Object>>() {});
		
		resultActions
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response").isMap())
		.andDo(document);
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/order/imminentOrder"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.response.campName").value(responseMap.get("campName")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.response.checkInDate").value(responseMap.get("checkInDate")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.response.checkInDDay").value(responseMap.get("checkInDDay")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void campScheduleList_test() throws Exception {
		// given

		// when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders
				.get("/order/campSchedule"));
		String responseBody = resultActions
				.andReturn()
				.getResponse()
				.getContentAsString();

		System.out.println("ResultAction:" + responseBody);
		
		// then
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> bodyMap = om.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
		Map<String, Object> responseMap = om.convertValue(bodyMap.get("response"), new TypeReference<Map<String, Object>>() {});
		List<Map<String, Object>> listDatsMap  = om.convertValue(responseMap.get("campScheduleDTOs"), new TypeReference<List<Map<String, Object>>>() {});
		
    	resultActions
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response").isMap())
		.andDo(document);
    	
		IntStream.range(0, listDatsMap .toArray().length).forEach(i -> {
			Map<String, Object> listDataDTO = listDatsMap .get(i);
				try {
					mockMvc.perform(MockMvcRequestBuilders.get("/order/campSchedule"))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs["+ i +"].campName").value(listDataDTO.get("campName")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs["+ i +"].campAddress").value(listDataDTO.get("campAddress")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs["+ i +"].checkInDate").value(listDataDTO.get("checkInDate")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs["+ i +"].checkInDDay").value(listDataDTO.get("checkInDDay")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.campScheduleDTOs["+ i +"].campField").value(listDataDTO.get("campField")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
							.andDo(MockMvcResultHandlers.print());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
		});
	}
	
	@Test
	public void campFieldList_test() throws Exception {
		
		//given
		OrderReqDTO.CampFieldListDTO requestDTO = new OrderReqDTO.CampFieldListDTO();
		requestDTO.setCampId(1);
		
		//when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders
						.get("/order/field-list")
						.param("campId", String.valueOf(requestDTO.getCampId()))
				);
		String responseBody = resultActions
				.andReturn()
				.getResponse()
				.getContentAsString();
		
		System.out.println("ResultAction : " + responseBody);
		
		//then
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> bodyMap = om.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
		Map<String, Object> responseMap = om.convertValue(bodyMap.get("response"), new TypeReference<Map<String, Object>>() {});
		Map<String, Object> campInfoDTO = om.convertValue(responseMap.get("campInfoDTO"), new TypeReference<Map<String, Object>>() {});
		List<Map<String, Object>> listDatsMap  = om.convertValue(responseMap.get("campFieldDTOs"), new TypeReference<List<Map<String, Object>>>() {});
		
    	resultActions
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response").isMap())
		.andDo(document);
		
    	mockMvc.perform(MockMvcRequestBuilders.get("/order/field-list").param("campId", String.valueOf(requestDTO.getCampId())))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.campName").value(campInfoDTO.get("campName")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.campAddress").value(campInfoDTO.get("campAddress")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.minPrice").value(campInfoDTO.get("minPrice")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.maxPrice").value(campInfoDTO.get("maxPrice")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.isOpen").value(campInfoDTO.get("isOpen")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.campImage").value(campInfoDTO.get("campImage")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campId").value(responseMap.get("campId")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldImage").value(responseMap.get("campFieldImage")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
		.andDo(MockMvcResultHandlers.print());
    	
    	IntStream.range(0, listDatsMap .toArray().length).forEach(i -> {
			Map<String, Object> listDataDTO = listDatsMap .get(i);
				try {
					mockMvc.perform(MockMvcRequestBuilders.get("/order/field-list").param("campId", String.valueOf(requestDTO.getCampId())))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs["+ i +"].fieldName").value(listDataDTO.get("fieldName")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldDTOs["+ i +"].price").value(listDataDTO.get("price")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
							.andDo(MockMvcResultHandlers.print());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
		});
	}
	
	@Test
	public void paymentDetail_test() throws Exception {
		
		//given
		OrderReqDTO.PaymentDetailDTO requestDTO = new OrderReqDTO.PaymentDetailDTO();
		requestDTO.setCampId(1);
		requestDTO.setCheckInDate("2024-01-24");
		requestDTO.setCheckOutDate("2024-01-26");
		requestDTO.setFieldName("캠핑사이트-1");
		
		//when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders
						.get("/order/payment")
						.param("campId", String.valueOf(requestDTO.getCampId()))
						.param("checkInDate", requestDTO.getCheckInDate())
						.param("checkOutDate", requestDTO.getCheckOutDate())
						.param("fieldName", requestDTO.getFieldName())
				);
		String responsebody = resultActions
						.andReturn()
						.getResponse()
						.getContentAsString();
		System.out.println("ResultActions :" + responsebody);
		
		
		//then
		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.campName").value("(주)아웃오브파크"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.campAddress").value("강원도 춘천시 남면 가옹개길 52-9"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.minPrice").value("50,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.maxPrice").value("60,000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.isOpen").value(true))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.campImage").value("/images/camp_image/camp1-1.jpg"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.checkInDate").value("2024-01-24"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.checkOutDate").value("2024-01-26"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.fieldName").value("캠핑사이트-1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.nights").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.totalPrice").value("100,000"))
				.andDo(MockMvcResultHandlers.print())
				.andDo(document);		
	}
	
	
}
