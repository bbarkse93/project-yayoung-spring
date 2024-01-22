package com.example.team_project.refund;

import com.example.team_project._core.utils.ApiUtils;
import com.example.team_project.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class RefundRestController {

    private final AdminService adminService;


    private String apiKey = "8360840240038660";
    private String secretKey = "sOvgLbX26n3aWW4f0m3KxveFA1N7jQkYLebBwMLQUyxyW0S1WdYMjIqa9w10k0eY14R0b40Rhs0AhdvX";



    // http://localhost:8080/refund
    @PostMapping("/refund")
    public ResponseEntity<?> wantRefund(@RequestBody RefundReqDTO.RefundRequestDTO requestDTO) {

        /***** 토큰 발급 *****/

        // * 액세스 토큰 요청 ---> Server to Server
        RestTemplate rt1 = new RestTemplate();
        // 헤더 구성
        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Content-Type", "application/json;charset=UTF-8" );
        // 바디 구성
        HashMap<String, String> params1 = new HashMap<String, String>();
        params1.put("imp_key", apiKey);
        params1.put("imp_secret", secretKey);

        // 헤더 + 바디 결합 => HTTP MSG 완성
        HttpEntity<HashMap<String, String>> requestMsg1
                = new HttpEntity<>(params1, headers1);
        System.out.println("HTTP MSG : " + requestMsg1);

        // 요청 처리 - ResponseEntity로 응답이 된다.
        ResponseEntity<TokenDTO> tokenDTO  = rt1.exchange("https://api.iamport.kr/users/getToken", HttpMethod.POST, requestMsg1, TokenDTO.class);

        String token = tokenDTO.getBody().getResponse().getAccessToken();

        /***** 환불 요청 *****/

        try {
            // 클라이언트로부터 받은 주문번호, 환불사유, 환불금액
            String merchantUid = requestDTO.getOrderNumber();
            String reason = "환불요청";
            String cancelRequestAmountString = requestDTO.getRefund();

            // * 액세스 토큰 요청 ---> Server to Server
            RestTemplate rt2 = new RestTemplate();

            // 헤더 구성
            HttpHeaders headers2 = new HttpHeaders();
            headers1.add("Content-Type", "application/json;charset=UTF-8");
            headers1.add("Authorization", token); // 토큰 들어가야 함

            // 바디 구성
            HashMap<String, String> params2 = new HashMap<>();
            params1.put("reason", reason);
            params1.put("merchant_uid", merchantUid); // 이 부분에서 imp_uid를 어떻게 획득하는지 확인이 필요합니다.
            params1.put("amount", cancelRequestAmountString);

            // 헤더 + 바디 결합 => HTTP MSG 완성
            HttpEntity<HashMap<String, String>> requestMsg2 = new HttpEntity<>(params1, headers1);

            // 요청 처리 - ResponseEntity로 응답이 된다.
            ResponseEntity<String> response1 = rt1.exchange("https://api.iamport.kr/payments/cancel", HttpMethod.POST, requestMsg1, String.class);

            String responseBody = response1.getBody();
            // "code" 부분을 추출
            int code = -1; // 기본값 설정 (에러 처리 등을 고려하여)
            if (responseBody != null && responseBody.contains("\"code\":")) {
                int codeIndex = responseBody.indexOf("\"code\":") + "\"code\":".length();
                int endIndex = responseBody.indexOf(",", codeIndex);
                if (endIndex == -1) {
                    // 마지막 요소인 경우
                    endIndex = responseBody.indexOf("}", codeIndex);
                }

                String codeValue = responseBody.substring(codeIndex, endIndex).trim();
                try {
                    code = Integer.parseInt(codeValue);
                } catch (NumberFormatException e) {
                    // 코드가 숫자가 아닌 경우 처리
                    e.printStackTrace(); // 혹은 로깅 등의 작업 수행
                }
            }

// 추출된 code 값 사용
            System.out.println("Code: " + code);
            System.out.println("==================================");

            if(code == 0){
                // 환불 완료 후 order에 refund 저장
                String result = adminService.saveRefund(requestDTO.getOrderId());
                // 응답을 다시 클라이언트에게 전달
                return ResponseEntity.ok().body(ApiUtils.success(result));
            }else{
                return ResponseEntity.ok().body(ApiUtils.success("환불에 실패했습니다."));

            }

        } catch (Exception e) {
            // 에러가 발생한 경우 클라이언트에게 에러 메시지 전달
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}