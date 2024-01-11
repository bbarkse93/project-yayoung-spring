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
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campName").value("(주)아웃오브파크"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campAddress").value("강원도 춘천시 남면 가옹개길 52-9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campCallNumber").value("1522-1861"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campWebsite").value("http://outofpark.com/main/"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campRefundPolicy").value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campFieldImage").value("/images/camp_map/camp1.png"))
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


    @Test
    public void campFieldList_test() throws Exception {

        //given
        CampReqDTO.CampFieldListDTO requestDTO = new CampReqDTO.CampFieldListDTO();
        requestDTO.setCampId(1);
        requestDTO.setCheckInDate("2024-01-10");
        requestDTO.setCheckOutDate("2024-01-15");

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/camp/field-list")
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.campInfoDTO.campFieldImage").value("/images/camp_map/camp1.png"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.checkInDate").value("2024-01-10"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.checkOutDate").value("2024-01-15"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.nights").value(5))
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
