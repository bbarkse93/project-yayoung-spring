package com.example.team_project.refund;

import com.example.team_project.MyWithRestDoc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
public class RefundRestController_test extends MyWithRestDoc{

	// post 요청 - 환불
	@Test
	public void wantRefund_test() throws Exception {

		//given
		RefundReqDTO.RefundRequestDTO requestDTO = new RefundReqDTO.RefundRequestDTO();
		requestDTO.setOrderId(1);
		requestDTO.setMerchantUid("11111");
		requestDTO.setCancelRequestAmount("8000");
		requestDTO.setReason("이유");
		ObjectMapper om = new ObjectMapper();
		String requestBody = om.writeValueAsString(requestDTO);

		//when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post("/user/refund")
						.content(requestBody)
						.contentType(MediaType.APPLICATION_JSON)
		);

		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("응답 : " + responseBody);
		System.out.println("resultActions : " + responseBody);

		//then
		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response").value("환불이 완료되었습니다."))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
				.andDo(MockMvcResultHandlers.print())
				.andDo(document);

	}
}
