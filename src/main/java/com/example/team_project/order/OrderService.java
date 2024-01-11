package com.example.team_project.order;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_project._core.erroes.exception.Exception404;
import com.example.team_project._core.utils.TimestampUtils;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.CampJPARepository;
import com.example.team_project.camp._dto.CampReqDTO;
import com.example.team_project.camp._dto.CampRespDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampFieldListDTO;
import com.example.team_project.camp_field.CampField;
import com.example.team_project.camp_field.CampFieldJPARepository;
import com.example.team_project.order._dto.OrderReqDTO;
import com.example.team_project.order._dto.OrderReqDTO.PaymentDetailDTO;
import com.example.team_project.order._dto.OrderRespDTO;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderJPARepository orderJPARepository;
    private final CampJPARepository campJPARepository;
    private final CampFieldJPARepository campFieldJPARepository;

    // 아이디로 다가오는 캠핑 일정 조회
	public OrderRespDTO.ImminentOrderDetailDTO imminentOrderDetail(Integer userId) {
		
		Order order = orderJPARepository.findFirstByUserIdAndCheckInDateAfterOrderByCheckInDateAsc(userId, TimestampUtils.findCurrnetTime());
		if(order == null) throw new Exception404("예정된 캠핑이 없습니다");
		return new OrderRespDTO.ImminentOrderDetailDTO(order);
	}

	// 아이디로 캠핑 일정 목록 조회
	public OrderRespDTO.CampScheduleListDTO campScheduleList(Integer userId) {
		List<Order> orders = orderJPARepository.findAllByUserIdAndCheckInDateAfterOrderByCheckInDateAsc(userId, TimestampUtils.findCurrnetTime());
		if(orders == null) throw new Exception404("예정된 캠핑이 없습니다");
		return new OrderRespDTO.CampScheduleListDTO(orders);
	}

	//캠프장 아이디를 받아 캠프 구역 목록 조회 + 캠프장 지도 + 상세정보 조회
	public CampRespDTO.CampFieldListDTO  campFieldList(OrderReqDTO.CampFieldListDTO requestDTO) {
		// 캠프장 정보 조회
		Camp camp = campJPARepository.findById(requestDTO.getCampId()).orElseThrow(() ->
				new Exception404("해당 캠프장이 존재하지 않습니다."));
		// 캠프 구역 목록 조회
		List<CampField> campFields = campFieldJPARepository.findAllByCampId(requestDTO.getCampId());
		if(campFields == null)
				throw new Exception404("잘못된 캠프장 명입니다.");
		return new CampRespDTO.CampFieldListDTO(campFields, camp, requestDTO);
	}

	// 결제 화면에 출력할 정보 조회
	public CampRespDTO.PaymentDetailDTO paymentDetail(OrderReqDTO.PaymentDetailDTO requestDTO) {
		// 캠프장 정보 조회
		Camp camp = campJPARepository.findById(requestDTO.getCampId()).orElseThrow(() ->
				new Exception404("해당 캠프장이 존재하지 않습니다."));
		// 캠프 구역 목록 조회
		List<CampField> campFields = campFieldJPARepository.findAllByCampId(requestDTO.getCampId());
		if(campFields == null)
				throw new Exception404("잘못된 캠프장 명입니다.");
		return new CampRespDTO.PaymentDetailDTO(campFields, camp, requestDTO);
	}

}
