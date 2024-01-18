package com.example.team_project.refund;

import com.example.team_project.MyWithRestDoc;
import com.example.team_project.admin.refund.RefundReqDTO;
import com.example.team_project.camp._dto.CampReqDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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

		//when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post("/refund")
						.param("orderId", String.valueOf(requestDTO.getOrderId()))
						.param("merchantUid", String.valueOf(requestDTO.getMerchantUid()))
						.param("cancelRequestAmount", String.valueOf(requestDTO.getCancelRequestAmount()))
						.param("reason", String.valueOf(requestDTO.getReason()))
		);

		String responseBody = resultActions.andReturn().getResponse().getContentAsString();

		System.out.println("resultActions : " + responseBody);
		//then
	}
}
