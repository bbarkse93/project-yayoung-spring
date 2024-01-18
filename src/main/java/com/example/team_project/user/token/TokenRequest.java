package com.example.team_project.user.token;

import com.example.team_project.user._dto.UserRespDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenRequest {

    public UserRespDTO.KakaoProfile kakaoTokenRequest(String token) {

        // 토큰으로 데이터 요청
        RestTemplate rt2 = new RestTemplate();
        // 헤더 구성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + token);
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 바디 구성 - 필수가 아님으로 생략
        // 헤더 바디 결합
        HttpEntity<MultiValueMap<String, String>> requestMsg2 = new HttpEntity<>(headers2);

        // 요청
        ResponseEntity<UserRespDTO.KakaoProfile> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
                requestMsg2, UserRespDTO.KakaoProfile.class);

        return response2.getBody();
    }

    public UserRespDTO.NaverProfile naverTokenRequest(String token) {

        // 토큰으로 데이터 요청
        String header = "Bearer " + token; // Bearer 다음에 공백 추가


        String apiURL = "https://openapi.naver.com/v1/nid/me";


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        ResponseEntity<UserRespDTO.NaverProfile> responseBody = get(apiURL,requestHeaders);


        System.out.println("네이버 요청 " + responseBody);
        return responseBody.getBody();

    }
    private static ResponseEntity<UserRespDTO.NaverProfile> get(String apiUrl, Map<String, String> requestHeaders){
        System.out.println("");
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            System.out.println("네이버 인증 : " + con.getInputStream());


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패 ", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static ResponseEntity<UserRespDTO.NaverProfile> readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            UserRespDTO.NaverProfile naverProfile = objectMapper.readValue(responseBody.toString(), UserRespDTO.NaverProfile.class);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");

            return new ResponseEntity<>(naverProfile, headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    }


