package com.example.team_project.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.team_project._core.utils.ApiUtils;
import com.example.team_project._core.utils.JwtTokenUtils;
import com.example.team_project.order._dto.OrderRespDTO;

import lombok.RequiredArgsConstructor;


@RequestMapping("/order")
@RequiredArgsConstructor
@RestController
public class OrderRestController {

    private final OrderService orderService;
    
    // 아이디로 다가오는 캠핑 일정 조회
    @GetMapping("/imminentOrder")
    public ResponseEntity<?> imminentOrderDetail(/*@RequestHeader("Authorization") String token*/){
    	//DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
    	//Integer userId = decodedJWT.getClaim("id").asInt();
    	// 테스트 용 하드 코딩
    	OrderRespDTO.ImminentOrderDetailDTO responseDTO = orderService.imminentOrderDetail(1);
    	//OrderRespDTO.ImminentOrderDetailDTO responseDTO = orderService.imminentOrderDetail(userId);
    	return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }
    
    // 아이디로 캠핑 일정 목록 조회
    @GetMapping("/campSchedule")
    public ResponseEntity<?> campScheduleList(/*@RequestHeader("Authorization") String token*/){
    	//DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
    	//Integer userId = decodedJWT.getClaim("id").asInt();
    	// 테스트 용 하드 코딩
    	OrderRespDTO.CampScheduleListDTO responseDTO  = orderService.campScheduleList(1);
    	//OrderRespDTO.CampScheduleListDTO responseDTO  = orderService.campScheduleList(userId);
    	return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }
    
    // 내 캠핑장 조회(지난 캠핑)
    @GetMapping("/myCampField")
    public ResponseEntity<?> myCampFieldList(/*@RequestHeader("Authorization") String token*/){
    	//DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
    	//Integer userId = decodedJWT.getClaim("id").asInt();
    	// 테스트 용 하드 코딩
    	OrderRespDTO.myCampFieldListDTO responseDTO = orderService.myCampFieldList(1);
    	//OrderRespDTO.myCampFieldListDTO responseDTO = orderService.myCampFieldList(userId);
    	return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }
    
    
    

    

}
