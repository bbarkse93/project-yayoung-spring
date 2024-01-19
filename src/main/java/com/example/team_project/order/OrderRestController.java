package com.example.team_project.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.team_project._core.utils.ApiUtils;
import com.example.team_project._core.utils.JwtTokenUtils;
import com.example.team_project.camp._dto.CampRespDTO;
import com.example.team_project.order._dto.OrderReqDTO;
import com.example.team_project.order._dto.OrderRespDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequestMapping("/order")
@RequiredArgsConstructor
@RestController
public class OrderRestController {

    private final OrderService orderService;
    
    // 아이디로 다가오는 캠핑 일정 조회
    @GetMapping("/imminentOrder")
    public ResponseEntity<?> imminentOrderDetail(@RequestHeader("Authorization") String token){
    	DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
    	Integer userId = decodedJWT.getClaim("id").asInt();
    	
    	OrderRespDTO.ImminentOrderDetailDTO responseDTO = orderService.imminentOrderDetail(userId);
    	return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }
    
    // 아이디로 캠핑 일정 목록 조회
    @GetMapping("/campSchedule")
    public ResponseEntity<?> campScheduleList(@RequestHeader("Authorization") String token){
    	DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
    	Integer userId = decodedJWT.getClaim("id").asInt();

    	OrderRespDTO.CampScheduleListDTO responseDTO  = orderService.campScheduleList(userId);
        System.out.println("목록 조회 리스펀스에 값은 : ?" + responseDTO.getCampScheduleDTOs());
    	return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }
    
    //캠프장 아이디를 받아 캠프 구역 목록 조회 + 캠프장 지도 + 상세정보 조회
    @GetMapping("/field-list")
    public ResponseEntity<?> campFieldList(@ModelAttribute OrderReqDTO.CampFieldListDTO requestDTO
            ,@RequestHeader("Authorization") String token){
    	// 토큰 인증
    	DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
    	CampRespDTO.CampFieldListDTO responseDTO = orderService.campFieldList(requestDTO);
    	return ResponseEntity.ok(ApiUtils.success(responseDTO));
    }
    
    
    // 캠핑 결제
    @PostMapping("/payment")
    public ResponseEntity<?> paymentWrite(@RequestBody @Valid OrderReqDTO.PaymentWriteDTO requestDTO 
    					,@RequestHeader("Authorization") String token){
    	// 토큰 인증
    	DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
    	Integer userId = decodedJWT.getClaim("id").asInt();
    	// 결제 정보의 유효성 검증
    	orderService.paymentWriteValidate(userId, requestDTO);
    	// DB 등록 및 캠핑장 지도 반환
    	OrderRespDTO.PaymentWriteDTO responseDTO = orderService.paymentWrite(userId, requestDTO);
    	return ResponseEntity.ok(ApiUtils.success(responseDTO));
    }

}
