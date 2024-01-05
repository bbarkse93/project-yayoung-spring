package com.example.team_project.order;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_project._core.erroes.exception.Exception404;
import com.example.team_project.order._dto.OrderRespDTO;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderJPARepository orderJPARepository;

    // 아이디로 다가오는 캠핑 일정 조회
	public OrderRespDTO.ImminentOrderDetailDTO imminentOrderDetail(Integer userId) {
		Order order = orderJPARepository.findFirstByUserIdOrderByCheckInDateAsc(userId);
		if(order == null) throw new Exception404("예정된 캠핑이 없습니다");
		return new OrderRespDTO.ImminentOrderDetailDTO(order);
	}

	// 아이디로 캠핑 일정 목록 조회
	public OrderRespDTO.CampScheduleListDTO campScheduleList(int userId) {
		List<Order> orders = orderJPARepository.findAllByUserIdOrderByCheckInDateAsc(userId);
		if(orders == null) throw new Exception404("예정된 캠핑이 없습니다");
		return new OrderRespDTO.CampScheduleListDTO(orders);
	}

	// 아이디로 내 캠핑장 목록 검색
	public OrderRespDTO.myCampFieldListDTO myCampFieldList(int userId) {
		//List<DTO> myCampList = orderJPARepository.findAllByUserId(userId);
		
		return new OrderRespDTO.myCampFieldListDTO();
	}
	


}
