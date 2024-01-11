package com.example.team_project.camp;

import java.util.List;

import com.example.team_project.MyWithRestDoc;
import com.example.team_project.camp._dto.CampReqDTO;
import com.example.team_project.notice._dto.NoticeRespDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.IntStream;

import com.example.team_project.MyWithRestDoc;
import com.example.team_project.camp._dto.CampReqDTO;

@SpringBootTest

public class CampRestController_test extends MyWithRestDoc {

    @Test
    public void getAllCamps_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/camp/list"));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> bodyMap = om.readValue(responseBody, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> responseMap = om.convertValue(bodyMap.get("response"),
                new TypeReference<Map<String, Object>>() {
                });
        List<Map<String, Object>> listDatsMap = om.convertValue(responseMap.get("campDTOList"),
                new TypeReference<List<Map<String, Object>>>() {
                });

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response").isMap())
                .andDo(document);
        IntStream.range(0, listDatsMap.toArray().length).forEach(i -> {
            Map<String, Object> listDataDTO = listDatsMap.get(i);
            try {
                mockMvc.perform(MockMvcRequestBuilders.get("/camp/list"))
                        .andExpect(MockMvcResultMatchers
                                .jsonPath("$.response.campDTOList[" + i + "].id")
                                .value(listDataDTO.get("id")))
                        .andExpect(MockMvcResultMatchers
                                .jsonPath("$.response.campDTOList[" + i + "].campName")
                                .value(listDataDTO.get("campName")))
                        .andExpect(MockMvcResultMatchers
                                .jsonPath("$.response.campDTOList[" + i
                                        + "].campAddress")
                                .value(listDataDTO.get("campAddress")))
                        .andExpect(MockMvcResultMatchers
                                .jsonPath("$.response.campDTOList[" + i + "].campImage")
                                .value(listDataDTO.get("campImage")))
                        .andExpect(MockMvcResultMatchers
                                .jsonPath("$.response.campDTOList[" + i
                                        + "].campRating")
                                .value(listDataDTO.get("campRating")))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                        .andDo(MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Test
    public void getCampDetail_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/camp/" + id)

        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campName").value("(주)아웃오브파크"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campAddress").value("강원도 춘천시 남면 가옹개길 52-9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campCallNumber").value("1522-1861"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campWebsite").value("http://outofpark.com/main/"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campRefundPolicy").value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campFieldImage").value("/images/camp_map/camp1.png"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campPrice.minPrice").value("50000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfo.campPrice.maxPrice").value("60000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campRating.cleanlinessAverage").value("4.2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campRating.managementnessAverage").value("4.0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campRating.friendlinessAverage").value("3.8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.reviewCount").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[0].campImageId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[0].campImage").value("/images/camp_image/camp1-1.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[1].campImageId").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[1].campImage").value("/images/camp_image/camp1-2.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[2].campImageId").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[2].campImage").value("/images/camp_image/camp1-3.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[3].campImageId").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[3].campImage").value("/images/camp_image/camp1-4.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[4].campImageId").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[4].campImage").value("/images/camp_image/camp1-5.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[5].campImageId").value("6"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[5].campImage").value("/images/camp_image/camp1-6.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[6].campImageId").value("7"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[6].campImage").value("/images/camp_image/camp1-7.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[7].campImageId").value("8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.images[7].campImage").value("/images/camp_image/camp1-8.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[0].optionId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[0].optionName").value("산"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[1].optionId").value("8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[1].optionName").value("카라반"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[2].optionId").value("9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[2].optionName").value("오토캠핑"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[3].optionId").value("11"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[3].optionName").value("데크"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[4].optionId").value("11"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[4].optionName").value("데크"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[5].optionId").value("14"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[5].optionName").value("전기"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[6].optionId").value("15"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[6].optionName").value("Wi-Fi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[7].optionId").value("16"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[7].optionName").value("화로대"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[8].optionId").value("19"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[8].optionName").value("샤워장"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[9].optionId").value("20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[9].optionName").value("온수"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[10].optionId").value("21"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[10].optionName").value("매점"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[11].optionId").value("22"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[11].optionName").value("물놀이장"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[12].optionId").value("27"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[12].optionName").value("족구장"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[13].optionId").value("33"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[13].optionName").value("릴선"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[14].optionId").value("36"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[14].optionName").value("숯"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[15].optionId").value("37"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[15].optionName").value("장작"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[16].optionId").value("38"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[16].optionName").value("얼음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[17].optionId").value("39"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[17].optionName").value("술"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[18].optionId").value("40"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.options[18].optionName").value("등유"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);
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

        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        // then

        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[0].totalRating").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[0].checkInDate")
                        .value("2024년 01월 11일"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[0].checkOutDate").value("01월 15일"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[0].campAddress")
                        .value("강원도 춘천시 남면 가옹개길 52-9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[0].campName").value("(주)아웃오브파크"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[0].reviewImage")
                        .value("camp_image/camp1-1.jpg"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[1].totalRating").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[1].checkInDate")
                        .value("2024년 01월 17일"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[1].checkOutDate").value("01월 18일"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[1].campAddress")
                        .value("강원도 춘천시 남면 가옹개길 52-9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[1].campName").value("(주)아웃오브파크"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[1].reviewImage")
                        .value("camp_image/camp1-1.jpg"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[2].totalRating").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[2].checkInDate")
                        .value("2024년 01월 20일"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[2].checkOutDate").value("01월 25일"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[2].campAddress")
                        .value("강원도 춘천시 남면 가옹개길 52-9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[2].campName").value("(주)아웃오브파크"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[2].reviewImage")
                        .value("camp_image/camp1-1.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[3].totalRating").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[3].checkInDate")
                        .value("2024년 02월 03일"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[3].checkOutDate").value("02월 10일"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[3].campAddress")
                        .value("강원도 춘천시 남면 가옹개길 52-9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[3].campName").value("(주)아웃오브파크"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.myCampDTOs[3].reviewImage")
                        .value("camp_image/camp1-1.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);
    }

}
