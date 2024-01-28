package com.example.team_project.camp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.team_project.MyWithRestDoc;
import com.example.team_project.camp._dto.CampReqDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class CampRestController_test extends MyWithRestDoc {

	private final static String TESTJWTTOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJwcm9qZWN0LWtleSIsImlkIjoxLCJ1c2VybmFtZSI6bnVsbCwiZXhwIjo0ODU5MTM4MTc3fQ.596oW5tzgj5JnJu96jMaJGWs6f29kAkf8czoYXP0hpVzZvnV93GNSTQHW23UsgeEKlc_uaZYWtQJarxufGq94Q";

	//캠핑장 목록 조회(필터 적용)
	@Test
    public void getAllCamps_test() throws Exception {
        // given
    	CampReqDTO.CampListDTO requestDTO = new CampReqDTO.CampListDTO();
    	List<String> optionNames = new ArrayList<>();
    	List<String> regionNames = new ArrayList<>();
    	requestDTO.setOptionNames(optionNames);
    	requestDTO.setRegionNames(regionNames);
    	
        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/camp/list")
                        .header("Authorization","Bearer " + TESTJWTTOKEN)
                        .param("optionNames", (requestDTO.getOptionNames() != null) ? String.join(",", requestDTO.getOptionNames()) : null)
                        .param("regionNames", (requestDTO.getRegionNames() != null) ? String.join(",", requestDTO.getRegionNames()) : null));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> bodyMap = om.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
        Map<String, Object> responseMap = om.convertValue(bodyMap.get("response"), new TypeReference<Map<String, Object>>() {});
        List<Map<String, Object>> listDatsMap = om.convertValue(responseMap.get("campDTO"), new TypeReference<List<Map<String, Object>>>() {});

        
        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response").isMap())
                .andDo(document);
        IntStream.range(0, listDatsMap.toArray().length).forEach(i -> {
            Map<String, Object> listDataDTO = listDatsMap.get(i);
            try {
                mockMvc.perform(MockMvcRequestBuilders.get("/camp/list").header("Authorization","Bearer " + TESTJWTTOKEN)
                			.param("optionNames", (requestDTO.getOptionNames() != null) ? String.join(",", requestDTO.getOptionNames()) : null)
                			.param("regionNames", (requestDTO.getRegionNames() != null) ? String.join(",", requestDTO.getRegionNames()) : null))
                        .andExpect(MockMvcResultMatchers
                                .jsonPath("$.response.campDTO[" + i + "].id")
                                .value(listDataDTO.get("id")))
                        .andExpect(MockMvcResultMatchers
                                .jsonPath("$.response.campDTO[" + i + "].campName")
                                .value(listDataDTO.get("campName")))
                        .andExpect(MockMvcResultMatchers
                                .jsonPath("$.response.campDTO[" + i
                                        + "].campAddress")
                                .value(listDataDTO.get("campAddress")))
                        .andExpect(MockMvcResultMatchers
                                .jsonPath("$.response.campDTO[" + i + "].campImage")
                                .value(listDataDTO.get("campImage")))
                        .andExpect(MockMvcResultMatchers
                                .jsonPath("$.response.campDTO[" + i
                                        + "].campRating")
                                .value(listDataDTO.get("campRating")))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                        .andDo(MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


	// 캠핑장 상세 조회
    @Test
    public void getCampDetail_test() throws Exception {
        // given
        int id = 1;
        
        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/camp/"+id)
						.header("Authorization","Bearer " + TESTJWTTOKEN)
		);

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        
        // then
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> bodyMap = om.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
		Map<String, Object> responseMap = om.convertValue(bodyMap.get("response"), new TypeReference<Map<String, Object>>() {});
		Map<String, Object> campInfoMap = om.convertValue(responseMap.get("campInfo"), new TypeReference<Map<String, Object>>() {});
		Map<String, Object> campPriceMap = om.convertValue(campInfoMap.get("campPrice"), new TypeReference<Map<String, Object>>() {});
		Map<String, Object> campRatingMap = om.convertValue(responseMap.get("campRating"), new TypeReference<Map<String, Object>>() {});
		List<Map<String, Object>> imagesMap  = om.convertValue(responseMap.get("images"), new TypeReference<List<Map<String, Object>>>() {});
		//options
		Map<String, Object> optionsMap = om.convertValue(responseMap.get("options"), new TypeReference<Map<String, Object>>() {});
		List<Map<String, Object>> exercise_facility  = om.convertValue(optionsMap.get("exercise_facility"), new TypeReference<List<Map<String, Object>>>() {});
		List<Map<String, Object>> main_facility  = om.convertValue(optionsMap.get("main_facility"), new TypeReference<List<Map<String, Object>>>() {});
		List<Map<String, Object>> site  = om.convertValue(optionsMap.get("site"), new TypeReference<List<Map<String, Object>>>() {});
		List<Map<String, Object>> environment  = om.convertValue(optionsMap.get("environment"), new TypeReference<List<Map<String, Object>>>() {});
		List<Map<String, Object>> sell  = om.convertValue(optionsMap.get("sell"), new TypeReference<List<Map<String, Object>>>() {});
		List<Map<String, Object>> have_facility  = om.convertValue(optionsMap.get("have_facility"), new TypeReference<List<Map<String, Object>>>() {});
		List<Map<String, Object>> type  = om.convertValue(optionsMap.get("type"), new TypeReference<List<Map<String, Object>>>() {});
		List<Map<String, Object>> rental  = om.convertValue(optionsMap.get("rental"), new TypeReference<List<Map<String, Object>>>() {});

		
        
		
		resultActions
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response").isMap())
		.andDo(document);        
		
    	mockMvc.perform(MockMvcRequestBuilders.get("/camp/" + id).header("Authorization","Bearer " + TESTJWTTOKEN))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.id").value(campInfoMap.get("id")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campName").value(campInfoMap.get("campName")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campAddress").value(campInfoMap.get("campAddress")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campCallNumber").value(campInfoMap.get("campCallNumber")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campWebsite").value(campInfoMap.get("campWebsite")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campRefundPolicy").value(campInfoMap.get("campRefundPolicy")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campWater").value(campInfoMap.get("campWater")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campGarbageBag").value(campInfoMap.get("campGarbageBag")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.bookmark").value(campInfoMap.get("bookmark")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.holiday").value(campInfoMap.get("holiday")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campCheckIn").value(campInfoMap.get("campCheckIn")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campCheckOut").value(campInfoMap.get("campCheckOut")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campFieldImage").value(campInfoMap.get("campFieldImage")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campPrice.minPrice").value(campPriceMap.get("minPrice")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campPrice.maxPrice").value(campPriceMap.get("maxPrice")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.totalRating").value(campInfoMap.get("totalRating")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campRating.cleanlinessAverage").value(campRatingMap.get("cleanlinessAverage")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campRating.managementnessAverage").value(campRatingMap.get("managementnessAverage")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.campRating.friendlinessAverage").value(campRatingMap.get("friendlinessAverage")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response.reviewCount").value(responseMap.get("reviewCount")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
		.andDo(MockMvcResultHandlers.print());

		IntStream.range(0, imagesMap .toArray().length).forEach(i -> {
			Map<String, Object> imagesMapDTO = imagesMap .get(i);
				try {
					mockMvc.perform(MockMvcRequestBuilders.get("/camp/" + id).header("Authorization","Bearer " + TESTJWTTOKEN))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.images["+ i +"].campImageId").value(imagesMapDTO.get("campImageId")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.images["+ i +"].campImage").value(imagesMapDTO.get("campImage")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
							.andDo(MockMvcResultHandlers.print());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
		});
		
		IntStream.range(0, exercise_facility .toArray().length).forEach(i -> {
			Map<String, Object> exercise_facilityDTO = exercise_facility .get(i);
				try {
					mockMvc.perform(MockMvcRequestBuilders.get("/camp/" + id).header("Authorization","Bearer " + TESTJWTTOKEN))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.exercise_facility["+ i +"].optionId").value(exercise_facilityDTO.get("optionId")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.exercise_facility["+ i +"].optionName").value(exercise_facilityDTO.get("optionName")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.exercise_facility["+ i +"].categoryId").value(exercise_facilityDTO.get("categoryId")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.exercise_facility["+ i +"].categoryName").value(exercise_facilityDTO.get("categoryName")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
							.andDo(MockMvcResultHandlers.print());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
		});
		
		IntStream.range(0, main_facility .toArray().length).forEach(i -> {
			Map<String, Object> main_facilityDTO = main_facility .get(i);
			try {
				mockMvc.perform(MockMvcRequestBuilders.get("/camp/" + id).header("Authorization","Bearer " + TESTJWTTOKEN))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.main_facility["+ i +"].optionId").value(main_facilityDTO.get("optionId")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.main_facility["+ i +"].optionName").value(main_facilityDTO.get("optionName")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.main_facility["+ i +"].categoryId").value(main_facilityDTO.get("categoryId")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.main_facility["+ i +"].categoryName").value(main_facilityDTO.get("categoryName")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
				.andDo(MockMvcResultHandlers.print());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});		
		
		IntStream.range(0, site .toArray().length).forEach(i -> {
			Map<String, Object> siteDTO = site .get(i);
				try {
					mockMvc.perform(MockMvcRequestBuilders.get("/camp/" + id).header("Authorization","Bearer " + TESTJWTTOKEN))
					.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.site["+ i +"].optionId").value(siteDTO.get("optionId")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.site["+ i +"].optionName").value(siteDTO.get("optionName")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.site["+ i +"].categoryId").value(siteDTO.get("categoryId")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.site["+ i +"].categoryName").value(siteDTO.get("categoryName")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
					.andDo(MockMvcResultHandlers.print());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
		});		
		
		IntStream.range(0, environment .toArray().length).forEach(i -> {
			Map<String, Object> environmentDTO = environment .get(i);
			try {
				mockMvc.perform(MockMvcRequestBuilders.get("/camp/" + id).header("Authorization","Bearer " + TESTJWTTOKEN))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.environment["+ i +"].optionId").value(environmentDTO.get("optionId")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.environment["+ i +"].optionName").value(environmentDTO.get("optionName")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.environment["+ i +"].categoryId").value(environmentDTO.get("categoryId")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.environment["+ i +"].categoryName").value(environmentDTO.get("categoryName")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
				.andDo(MockMvcResultHandlers.print());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});		
		
		IntStream.range(0, sell .toArray().length).forEach(i -> {
			Map<String, Object> sellDTO = sell .get(i);
			try {
				mockMvc.perform(MockMvcRequestBuilders.get("/camp/" + id).header("Authorization","Bearer " + TESTJWTTOKEN))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.sell["+ i +"].optionId").value(sellDTO.get("optionId")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.sell["+ i +"].optionName").value(sellDTO.get("optionName")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.sell["+ i +"].categoryId").value(sellDTO.get("categoryId")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.sell["+ i +"].categoryName").value(sellDTO.get("categoryName")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
				.andDo(MockMvcResultHandlers.print());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});		
		
		IntStream.range(0, have_facility .toArray().length).forEach(i -> {
			Map<String, Object> have_facilityDTO = have_facility .get(i);
			try {
				mockMvc.perform(MockMvcRequestBuilders.get("/camp/" + id).header("Authorization","Bearer " + TESTJWTTOKEN))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.have_facility["+ i +"].optionId").value(have_facilityDTO.get("optionId")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.have_facility["+ i +"].optionName").value(have_facilityDTO.get("optionName")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.have_facility["+ i +"].categoryId").value(have_facilityDTO.get("categoryId")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.have_facility["+ i +"].categoryName").value(have_facilityDTO.get("categoryName")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
				.andDo(MockMvcResultHandlers.print());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});		
		
		IntStream.range(0, type .toArray().length).forEach(i -> {
			Map<String, Object> typeDTO = type .get(i);
			try {
				mockMvc.perform(MockMvcRequestBuilders.get("/camp/" + id).header("Authorization","Bearer " + TESTJWTTOKEN))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.type["+ i +"].optionId").value(typeDTO.get("optionId")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.type["+ i +"].optionName").value(typeDTO.get("optionName")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.type["+ i +"].categoryId").value(typeDTO.get("categoryId")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.type["+ i +"].categoryName").value(typeDTO.get("categoryName")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
				.andDo(MockMvcResultHandlers.print());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});		
		
		IntStream.range(0, rental .toArray().length).forEach(i -> {
			Map<String, Object> rentalDTO = rental .get(i);
			try {
				mockMvc.perform(MockMvcRequestBuilders.get("/camp/" + id).header("Authorization","Bearer " + TESTJWTTOKEN))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.rental["+ i +"].optionId").value(rentalDTO.get("optionId")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.rental["+ i +"].optionName").value(rentalDTO.get("optionName")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.rental["+ i +"].categoryId").value(rentalDTO.get("categoryId")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.options.rental["+ i +"].categoryName").value(rentalDTO.get("categoryName")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
				.andDo(MockMvcResultHandlers.print());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});		
    }

	// 북마크 추가
    @Test
    public void addBookmark_test() throws Exception {
    	//given
    	CampReqDTO.CampBookmarkDTO requestDTO = new CampReqDTO.CampBookmarkDTO();
    	requestDTO.setCampId(1);
		ObjectMapper om = new ObjectMapper();
		String requestBody = om.writeValueAsString(requestDTO);

    	//when
    	ResultActions resultActions = mockMvc.perform(
    			MockMvcRequestBuilders.post("/camp/bookmark")
						.header("Authorization","Bearer " + TESTJWTTOKEN)
    					.content(requestBody)
						.contentType(MediaType.APPLICATION_JSON)
    			);
    	
    	String responseBody = resultActions.andReturn().getResponse().getContentAsString();
    	
    	System.out.println("resultActions : " + responseBody);

    	//then
    	resultActions
    			.andExpect(MockMvcResultMatchers.status().isOk())
//    			.andExpect(MockMvcResultMatchers.jsonPath("$.response").value("북마크 성공"))
    			.andExpect(MockMvcResultMatchers.jsonPath("$.response.bookmark").value(true))
    			.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
    			.andDo(MockMvcResultHandlers.print())
    			.andDo(document);
    }

	// 북마크 제거
    @Test
    public void removeBookmark_test() throws Exception {
    	
    	//given
    	CampReqDTO.CampBookmarkDeleteDTO requestDTO = new CampReqDTO.CampBookmarkDeleteDTO();
    	requestDTO.setCampId(2);
		ObjectMapper om = new ObjectMapper();
		String requestBody = om.writeValueAsString(requestDTO);
    	
    	//when
    	ResultActions resultActions = mockMvc.perform(
    			MockMvcRequestBuilders.delete("/camp/bookmark")
						.header("Authorization","Bearer " + TESTJWTTOKEN)
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody)
    			);
    	String responseBody = resultActions.andReturn().getResponse().getContentAsString();
    	System.out.println("ResultActions : " + responseBody);
    	//then
    	resultActions
    			.andExpect(MockMvcResultMatchers.status().isOk())
    			.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
    			.andExpect(MockMvcResultMatchers.jsonPath("$.response.bookmark").value(false))    			
    			.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
    			.andDo(MockMvcResultHandlers.print())
    			.andDo(document);
    						
    }

	// 북마크 리스트
	@Test
	public void bookmarkList_test() throws Exception{

		// when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.get("/camp/bookmarks")
						.header("Authorization","Bearer " + TESTJWTTOKEN)
		);

		String responseBody = resultActions.andReturn().getResponse().getContentAsString();

		System.out.println("ResultActions : " + responseBody);

	}

	//
    @Test
    public void campBookmarkPage_test() throws Exception {
    	
    	//given
    	
    	//when
    	ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.get("/camp/bookmark-list")
				.header("Authorization","Bearer " + TESTJWTTOKEN)
		);


    	String responseBody = resultActions.andReturn().getResponse().getContentAsString();
    	
    	System.out.println("ResultActions : " + responseBody);
    	
    	//then
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> bodyMap = om.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
		Map<String, Object> responseMap = om.convertValue(bodyMap.get("response"), new TypeReference<Map<String, Object>>() {});
		List<Map<String, Object>> listDatsMap  = om.convertValue(responseMap.get("campBookmarkList"), new TypeReference<List<Map<String, Object>>>() {});
		
    	resultActions
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response").isMap())
		.andDo(document);
    	
		IntStream.range(0, listDatsMap .toArray().length).forEach(i -> {
			Map<String, Object> listDataDTO = listDatsMap .get(i);
				try {
					mockMvc.perform(MockMvcRequestBuilders.get("/camp/bookmark-list").header("Authorization","Bearer " + TESTJWTTOKEN))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.campBookmarkList["+ i +"].campId").value(listDataDTO.get("campId")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.campBookmarkList["+ i +"].campName").value(listDataDTO.get("campName")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.campBookmarkList["+ i +"].campAddress").value(listDataDTO.get("campAddress")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.campBookmarkList["+ i +"].campImage").value(listDataDTO.get("campImage")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.campBookmarkList["+ i +"].campRating").value(listDataDTO.get("campRating")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
							.andDo(MockMvcResultHandlers.print());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
		});
    	
    }
    
    
    @Test
    public void myCampList_test() throws Exception {

        // given
        CampReqDTO.MyCampListDTO requestDTO = new CampReqDTO.MyCampListDTO();
        requestDTO.setYear(2024);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/camp/myCamp")
                        .param("year", String.valueOf(requestDTO.getYear()))
						.header("Authorization","Bearer " + TESTJWTTOKEN)


		);

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        // then
     	ObjectMapper om = new ObjectMapper();
     	Map<String, Object> bodyMap = om.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
     	Map<String, Object> responseMap = om.convertValue(bodyMap.get("response"), new TypeReference<Map<String, Object>>() {});
     	List<Map<String, Object>> listDatsMap  = om.convertValue(responseMap.get("myCampDTOs"), new TypeReference<List<Map<String, Object>>>() {});
    	
     	resultActions
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response").isMap())
		.andDo(document);
     	
		IntStream.range(0, listDatsMap .toArray().length).forEach(i -> {
			Map<String, Object> listDataDTO = listDatsMap .get(i);
				try {
					mockMvc.perform(MockMvcRequestBuilders.get("/camp/myCamp").header("Authorization","Bearer " + TESTJWTTOKEN).param("year", String.valueOf(requestDTO.getYear())))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs["+ i +"].totalRating").value(listDataDTO.get("totalRating")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs["+ i +"].checkInDate").value(listDataDTO.get("checkInDate")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
							.andDo(MockMvcResultHandlers.print());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
		});
    }
    
    
    @Test
    public void searchCamp_test() throws Exception {
    	
    	//given
    	final String keyword = "아웃";
    	//when
    	ResultActions resultActions = mockMvc.perform(
    				MockMvcRequestBuilders.get("/camp/search")
    				.param("keyword", keyword )
    				.header("Authorization","Bearer " + TESTJWTTOKEN)
    			);
    	String responseBody = resultActions.andReturn().getResponse().getContentAsString();
    	System.out.println("ResultActions : " + responseBody);		
    	//then
    	
     	ObjectMapper om = new ObjectMapper();
     	Map<String, Object> bodyMap = om.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
     	Map<String, Object> responseMap = om.convertValue(bodyMap.get("response"), new TypeReference<Map<String, Object>>() {});
     	List<Map<String, Object>> listDatsMap  = om.convertValue(responseMap.get("campList"), new TypeReference<List<Map<String, Object>>>() {});
    	
     	resultActions
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response").isMap())
		.andDo(document);
     	
		IntStream.range(0, listDatsMap .toArray().length).forEach(i -> {
			Map<String, Object> listDataDTO = listDatsMap .get(i);
				try {
					mockMvc.perform(MockMvcRequestBuilders.get("/camp/search").header("Authorization","Bearer " + TESTJWTTOKEN).param("keyword", "아웃" ))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.campList["+ i +"].id").value(listDataDTO.get("id")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.campList["+ i +"].campName").value(listDataDTO.get("campName")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.response.campList["+ i +"].campAddress").value(listDataDTO.get("campAddress")))
							.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
							.andDo(MockMvcResultHandlers.print());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
		});
    }

	// 캠핑장 별 리뷰 목록 조회
	@Test
	public void getReview_test() throws Exception {
		// given
		int campId = 1;
		// when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.get("/camp/review/" + campId)
		);

		String responseBody = resultActions.andReturn().getResponse().getContentAsString();

		System.out.println("ResultActions : " + responseBody);
		//then
		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[0].id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[0].content").value("아름답고 좋은 장소였습니다. 다음에도 또 오고 싶네요."))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[0].cleanliness").value(4.0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[0].managementness").value(4.0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[0].friendliness").value(5.0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[0].nickname").value("ssar"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[0].userImage").value("/images/user/user-profile.jpg"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[0].totalRating").value(4.333333333333333))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[0].createdAt").value("2024-01-28"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[1].id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[1].content").value("전보다 더 아름답고 좋은 장소였습니다. 다음에도 또 오고 싶네요."))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[1].cleanliness").value(5.0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[1].managementness").value(4.0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[1].friendliness").value(5.0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[1].nickname").value("ssar"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[1].userImage").value("/images/user/user-profile.jpg"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[1].totalRating").value(4.666666666666667))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewDTO[1].createdAt").value("2024-01-28"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campName").value("(주)아웃오브파크"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.response.campReviewCount").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
				.andDo(document);

	}
}