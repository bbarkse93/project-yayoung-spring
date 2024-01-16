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
		List<Map<String, Object>> reservedCampFieldMap  = om.convertValue(responseMap.get("reservedCampFieldDTOs"), new TypeReference<List<Map<String, Object>>>() {});
		
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
    	
    	IntStream.range(0, reservedCampFieldMap .toArray().length).forEach(i -> {
    		Map<String, Object> listDataDTO = reservedCampFieldMap .get(i);
    		try {
    			mockMvc.perform(MockMvcRequestBuilders.get("/order/field-list").param("campId", String.valueOf(requestDTO.getCampId())))
    			.andExpect(MockMvcResultMatchers.jsonPath("$.response.reservedCampFieldDTOs["+ i +"].fieldName").value(listDataDTO.get("fieldName")))
    			.andExpect(MockMvcResultMatchers.jsonPath("$.response.reservedCampFieldDTOs["+ i +"].checkInDate").value(listDataDTO.get("checkInDate")))
    			.andExpect(MockMvcResultMatchers.jsonPath("$.response.reservedCampFieldDTOs["+ i +"].checkOutDate").value(listDataDTO.get("checkOutDate")))
    			.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
    			.andDo(MockMvcResultHandlers.print());
    		} catch (Exception e) {
    			throw new RuntimeException(e);
    		}
    	});
	}
	
	@Test
	public void paymentWrite_test() throws Exception {
		
		//given
		OrderReqDTO.OrderWriteDTO requestDTO = new OrderReqDTO.OrderWriteDTO();
		requestDTO.setCampId(1);
		requestDTO.setCheckIn("2024-01-25");
		requestDTO.setCheckOut("2024-01-28");
		requestDTO.setFieldName("캠핑사이트-1");
		
		//when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders
						.post("/order/payment")
						.contentType("application/json")
						.param("campId", String.valueOf(requestDTO.getCampId()))
						.param("checkIn", requestDTO.getCheckIn())
						.param("checkOut", requestDTO.getCheckOut())
						.param("fieldName", requestDTO.getFieldName())
				);
		String responseBody = resultActions
						.andReturn()
						.getResponse()
						.getContentAsString();
		System.out.println("ResultActions :" + responseBody);
		
		//then
		resultActions
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
			.andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldImage").value("/images/camp_map/camp1.png"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
			.andDo(MockMvcResultHandlers.print())
			.andDo(document);	
	}
	
	@Test
	public void orderDelete_test() throws Exception {
		
		//given
		OrderReqDTO.OrderDeleteDTO requestDTO = new OrderReqDTO.OrderDeleteDTO();
		requestDTO.setOrderId(6);
		//when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.delete("/order/refund")
				.param("orderId", String.valueOf(requestDTO.getOrderId()))
				);
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("ResultAciton : " + responseBody);
		//then
		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response").value("환불 처리 완료"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
				.andDo(MockMvcResultHandlers.print())
				.andDo(document);
	}	
}
