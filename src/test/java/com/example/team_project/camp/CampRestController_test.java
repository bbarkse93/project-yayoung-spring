package com.example.team_project.camp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
        String responsebody = resultActions
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("ResultAction : " + responsebody);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].campName").value("(주)아웃오브파크"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].campAddress").value("강원도 춘천시 남면 가옹개길 52-9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].campCallNumber").value("1522-1861"))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.response[0].campWebsite").value("http://outofpark.com/main/"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].campRefundPolicy")
                        .value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].campFieldImage").value("/images/camp_map/camp1.png"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].campName").value("파크킹"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].campAddress").value("경북 김천시 어모면 은림로 62-11"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].campCallNumber").value("054-434-6677"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].campWebsite")
                        .value("https://www.instagram.com/amazingpark6211/"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].campRefundPolicy")
                        .value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].campFieldImage").value("/images/camp_map/camp2.png"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].campName").value("디캠고흥"))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.response[2].campAddress").value("강원 횡성군 서원면 서원서로102번길 3-18"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].campCallNumber").value("033-345-3336"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].campWebsite")
                        .value("https://www.instagram.com/salon.de.bonj/"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].campRefundPolicy")
                        .value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].campFieldImage").value("/images/camp_map/camp3.png"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[3].id").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[3].campName").value("캠프달링"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[3].campAddress").value("경기 가평군 설악면 유명산길 61-75"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[3].campCallNumber").value("010-3148-9970"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[3].campWebsite").value("http://healingpia.co.kr"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[3].campRefundPolicy")
                        .value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[3].campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[3].campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[3].holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[3].campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[3].campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[3].campFieldImage").value("/images/camp_map/camp4.png"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[4].id").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[4].campName").value("STX 오토캠핑장"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[4].campAddress").value("충남 태안군 소원면 산간이길 158-24"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[4].campCallNumber").value("043-1668-3972"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[4].campWebsite").value(
                        "https://map.naver.com/v5/entry/place/1791561655?c=14047305.3983138,4408436.9604705,15,0,0,0,dh&amp;placePath=%2Fbooking%3Fentry=plt"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[4].campRefundPolicy")
                        .value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[4].campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[4].campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[4].holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[4].campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[4].campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[4].campFieldImage").value("/images/camp_map/camp5.png"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[5].id").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[5].campName").value("청주 옥화포시즌캠핑장"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[5].campAddress").value("강원 평창군 평창읍 뇌운계곡로 659-6"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[5].campCallNumber").value("033-332-0009"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[5].campWebsite")
                        .value("https://pcglamping.modoo.at/"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[5].campRefundPolicy")
                        .value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[5].campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[5].campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[5].holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[5].campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[5].campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[5].campFieldImage").value("/images/camp_map/camp6.png"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[6].id").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[6].campName").value("휘게 포레스트"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[6].campAddress").value("충북 충주시 앙성면 학교말2길 50"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[6].campCallNumber").value("033-332-0009"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[6].campWebsite")
                        .value("http://www.binaecamping.co.kr"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[6].campRefundPolicy")
                        .value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[6].campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[6].campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[6].holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[6].campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[6].campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[6].campFieldImage").value("/images/camp_map/camp7.png"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[7].id").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[7].campName").value("초이스캠프앤카라반"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[7].campAddress").value("충남 태안군 남면 몽산포길 63-23"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[7].campCallNumber").value("033-332-0009"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[7].campWebsite")
                        .value("https://solbeachcamp.modoo.at/"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[7].campRefundPolicy")
                        .value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[7].campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[7].campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[7].holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[7].campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[7].campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[7].campFieldImage").value("/images/camp_map/camp8.png"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[8].id").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[8].campName").value("초록여울캠핑장"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[8].campAddress").value("경북 포항시 북구 죽장면 새마을로 3351"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[8].campCallNumber").value("033-332-0119"))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.response[8].campWebsite").value("http://www.gaoncamp.com/"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[8].campRefundPolicy")
                        .value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[8].campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[8].campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[8].holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[8].campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[8].campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[8].campFieldImage").value("/images/camp_map/camp9.png"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[9].id").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[9].campName").value("소리의섬 캠핑장"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[9].campAddress").value("충북 충주시 앙성면 남한강변길 218-2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[9].campCallNumber").value("033-332-0229"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[9].campWebsite")
                        .value("http://limsglamping.modoo.at"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[9].campRefundPolicy")
                        .value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[9].campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[9].campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[9].holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[9].campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[9].campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[9].campFieldImage").value("/images/camp_map/camp10.png"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[10].id").value(11))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[10].campName").value("림스 캠핑장"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[10].campAddress").value("전남 강진군 강진읍 해강로 1038-30"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[10].campCallNumber").value("033-332-0229"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[10].campWebsite")
                        .value("http://limsglamping.modoo.at"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[10].campRefundPolicy")
                        .value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[10].campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[10].campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[10].holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[10].campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[10].campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[10].campFieldImage").value("/images/camp_map/camp11.png"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[11].id").value(12))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[11].campName").value("단아한"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[11].campAddress").value("전북 순창군 팔덕면 산동신흥길 6"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[11].campCallNumber").value("031-581-9977"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[11].campWebsite").value("Www.toycamp.kr"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[11].campRefundPolicy")
                        .value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[11].campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[11].campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[11].holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[11].campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[11].campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[11].campFieldImage").value("/images/camp_map/camp12.png"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[12].id").value(13))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[12].campName").value("원주 두리 캠핑장"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[12].campAddress").value("경기 가평군 가평읍 호반로 1700"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[12].campCallNumber").value("031-581-9977"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[12].campWebsite").value("Www.toycamp.kr"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[12].campRefundPolicy")
                        .value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[12].campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[12].campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[12].holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[12].campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[12].campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[12].campFieldImage").value("/images/camp_map/camp13.png"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[13].id").value(14))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[13].campName").value("너른뜰 캠핑장"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[13].campAddress").value("충남 부여군 세도면 대흥로 163"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[13].campCallNumber").value("031-581-9917"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[13].campWebsite").value("Www.toycamp.kr"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[13].campRefundPolicy")
                        .value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[13].campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[13].campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[13].holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[13].campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[13].campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[13].campFieldImage").value("/images/camp_map/camp14.png"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[14].id").value(15))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[14].campName").value("개네집 캠핑장"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[14].campAddress").value("경상북도 영덕군 병곡면 병곡리 58-5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[14].campCallNumber").value("031-557-7757"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[14].campWebsite").value("Www.toycamp.kr"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[14].campRefundPolicy")
                        .value("당일 취소 불가, 2일 전 100% 환불"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[14].campWater").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[14].campGarbageBag").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[14].holiday").value("없음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[14].campCheckIn").value("14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[14].campCheckOut").value("13:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[14].campFieldImage").value("/images/camp_map/camp15.png"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);
    }

    // resultMathers
    // .andExpect(MockMvcResultMatchers.status().isOk())
    // .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.response[" + index +
    // "].id").value(expectedId))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.response[" + index +
    // "].campName").value(expectedName))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.response[" + index +
    // "].campAddress").value(expectedAddress))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.response[" + index +
    // "].campCallNumber").value(expectedCallNumber))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.response[" + index +
    // "].campWebsite").value(expectedWebsite))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.response[" + index +
    // "].campRefundPolicy").value(expectedRefundPolicy))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.response[" + index +
    // "].campWater").value(expectedWater))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.response[" + index +
    // "].campGarbageBag").value(expectedGarbageBag))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.response[" + index +
    // "].holiday").value(expectedholiday))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.response[" + index +
    // "].campCheckIn").value(expectedCheckIn))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.response[" + index +
    // "].campCheckOut").value(expectedCheckOut))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.response[" + index +
    // "].campFieldImage").value(expectedFieldImage))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
    // .andDo(MockMvcResultHandlers.print());

    // }

    // for (int i = 0; i < numberOfCamps; i++) {
    // getAllCamps_test(resultActions,);
    // }

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
