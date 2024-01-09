package com.example.team_project.board;

import com.example.team_project.MyWithRestDoc;
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
public class BoardRestController_test extends MyWithRestDoc{

	// get 요청 - 공지사항 페이지
	@Test
	public void boardPage_test() throws Exception {
		// given
//        int id = 1;

		// when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders
						.get("/board/list")
		);

		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println(responseBody);
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> bodyMap = om.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
		Map<String, Object> responseMap = om.convertValue(bodyMap.get("response"), new TypeReference<Map<String, Object>>() {});
		List<Map<String, Object>> listDatsMap = om.convertValue(responseMap.get("paymentDTOList"), new TypeReference<List<Map<String, Object>>>() {});


		// then
		resultActions
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
			.andExpect(MockMvcResultMatchers.jsonPath("$.response").isMap())
			.andDo(document);
		IntStream.range(0, listDatsMap.toArray().length).forEach(i -> {
			Map<String, Object> listDataDTO = listDatsMap.get(i);
				try {
					mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.paymentDTOList["+ i +"].faqId").value(listDataDTO.get("faqId")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.paymentDTOList["+ i +"].title").value(listDataDTO.get("title")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.paymentDTOList["+ i +"].content").value(listDataDTO.get("content")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
							.andDo(MockMvcResultHandlers.print());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

		});

	}
}
