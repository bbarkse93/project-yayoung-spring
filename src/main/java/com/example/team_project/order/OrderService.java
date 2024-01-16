package com.example.team_project.order;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_project._core.errors.exception.Exception404;
import com.example.team_project._core.utils.TimestampUtils;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.CampJPARepository;
import com.example.team_project.camp._dto.CampRespDTO;
import com.example.team_project.camp_field.CampField;
import com.example.team_project.camp_field.CampFieldJPARepository;
import com.example.team_project.order._dto.OrderReqDTO;
import com.example.team_project.order._dto.OrderRespDTO;
import com.example.team_project.user.User;
import com.example.team_project.user.UserJPARepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderJPARepository orderJPARepository;
    private final CampJPARepository campJPARepository;
    private final CampFieldJPARepository campFieldJPARepository;
    private final UserJPARepository userJPARepository;

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
		// 제외할 예약 구역 조회
		List<Order> orders = orderJPARepository.findAllByCheckInDateAfterOrderByCheckInDateAsc(TimestampUtils.findCurrnetTime());
		return new CampRespDTO.CampFieldListDTO(campFields, camp, orders, requestDTO);
	}


	// 캠핑 결제
	public OrderRespDTO.PaymentWriteDTO paymentWrite(int userId, OrderReqDTO.OrderWriteDTO requestDTO) {
		// requestDTO 가공 로직
		Timestamp checkInDate  = TimestampUtils.convertToTimestamp(requestDTO.getCheckIn());
		Timestamp checkOutDate = TimestampUtils.convertToTimestamp(requestDTO.getCheckOut());
		CampField campField    = campFieldJPARepository.findByFieldNameAndCampId
									(requestDTO.getFieldName(),requestDTO.getCampId());
		User      user         = userJPARepository.findById(userId)
									.orElseThrow(()->new Exception404("해당 사용자가 없습니다."));
		// DB 입력
		Order response =  orderJPARepository.save(Order.builder()
							.checkInDate(checkInDate)
							.checkOutDate(checkOutDate)
							.user(user)
							.campField(campField)
							.build());
		if(response == null)
			throw new Exception404("예약에 실패했습니다.");
		// 결과 반환
		return new OrderRespDTO.PaymentWriteDTO(campField.getCamp().getCampFieldImage());
	}

	// 캠핑 환불 DB 처리
	public void orderDelete(Integer userId, @Valid OrderReqDTO.OrderDeleteDTO requestDTO) {
		Order order = orderJPARepository.findById(requestDTO.getOrderId())
						.orElseThrow(()-> new Exception404("잘못된 예약번호입니다."));
		orderJPARepository.delete(order);
		
	}

}
