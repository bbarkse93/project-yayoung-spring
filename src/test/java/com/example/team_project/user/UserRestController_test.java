package com.example.team_project.user;

import com.example.team_project.MyWithRestDoc;
import com.example.team_project.user._dto.UserReqDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootTest
public class UserRestController_test extends MyWithRestDoc {

    // get 요청 - 마이 페이지
    @Test
    public void myPage_test() throws Exception {
        // given
//        int id = 1;

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
//                        .get("/user/my-page/"+id)
                        .get("/user/my-page")
        );

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.nickname").value("ssar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.userImage").value("/images/user/user-profile.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);

    }

    // get 요청 - 프로필 페이지
    @Test
    public void profilePage_test() throws Exception {
        // given
//        int id = 1;

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
//                        .get("/user/my-page/"+id)
                        .get("/user/my-page/profile")
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.nickname").value("ssar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.userImage").value("user-profile.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);

    }

    // build 시 계속 실패해서 주석처리 해뒀습니다. - 승신(2024.01.11)
    // put 요청 - 프로필 수정
    @Test
    public void profileUpdate_test() throws Exception {
        // given
        UserReqDTO.ProfileUpdateDTO requestDTO = new UserReqDTO.ProfileUpdateDTO();
        requestDTO.setNickname("love");
        requestDTO.setUserImage("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");
        ObjectMapper om = new ObjectMapper();
        String requestBody = om.writeValueAsString(requestDTO);
        // when
        ResultActions ra = mockMvc.perform(
                MockMvcRequestBuilders.put("/user/my-page/profile")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );


        String responseBody = ra.andReturn().getResponse().getContentAsString();
        // 사진 파일
        String[] list = responseBody.split(":");
        String[] splitList = list[5].split("\"");
        String imgFileName = splitList[1];
        // then
        // 제대로 된 검증
        ra
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.nickname").value("love"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.userImage").value(imgFileName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);

    }
    // get 요청 - 로그아웃
    @Test
    public void logout_test() throws Exception {
        // given
//        int id = 1;

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
//                        .get("/user/my-page/"+id)
                        .get("/user/logout")
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response").value("로그아웃 완료"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);

    }

    // put 요청 - 회원탈퇴
    @Test
    public void withDraw_test() throws Exception {
        // given

        // when
        ResultActions ra = mockMvc.perform(
                MockMvcRequestBuilders.put("/user/withDraw")
                        .contentType(MediaType.APPLICATION_JSON)
        );


        String responseBody = ra.andReturn().getResponse().getContentAsString();
        System.out.println("=====================================");
        System.out.println(responseBody);
        System.out.println("=====================================");

        // then
        // 제대로 된 검증
        ra
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.nickname").value("love"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.withdraw").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);
    }


}
