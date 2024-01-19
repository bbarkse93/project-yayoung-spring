package com.example.team_project.order;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_project._core.errors.exception.Exception400;
import com.example.team_project._core.errors.exception.Exception404;
import com.example.team_project._core.errors.exception.Exception500;
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
		return new OrderRespDTO.ImminentOrderDetailDTO(order);
	}

	// 아이디로 캠핑 일정 목록 조회
	public OrderRespDTO.CampScheduleListDTO campScheduleList(Integer userId) {
		List<Order> orders = orderJPARepository.findAllByUserIdAndCheckInDateAfterOrderByCheckInDateAsc(userId, TimestampUtils.findCurrnetTime());
		return new OrderRespDTO.CampScheduleListDTO(orders);
	}

	//캠프장 아이디를 받아 캠프 구역 목록 조회 + 캠프장 지도 + 상세정보 조회
	public CampRespDTO.CampFieldListDTO  campFieldList(OrderReqDTO.CampFieldListDTO requestDTO) {
		// 캠프장 정보 조회
		Camp camp = campJPARepository.findById(requestDTO.getCampId()).orElseThrow(() ->
				new Exception400("해당 캠프장이 존재하지 않습니다."));
		// 제외할 예약 구역 조회
		List<Order> orders = orderJPARepository.findAllByCheckInDateAfterOrderByCheckInDateAsc(TimestampUtils.findCurrnetTime());
		return new CampRespDTO.CampFieldListDTO(camp, orders, requestDTO);
	}

	// 결제 정보 검증 및 유효성 검사
	public void paymentWriteValidate(Integer userId,@Valid OrderReqDTO.PaymentWriteDTO requestDTO ) {
		//사용자가 없으면 예외 처리
		userJPARepository.findById(userId)
					.orElseThrow(()->new Exception400("해당 사용자가 없습니다."));
		// 캠프 아이디와 캠프 구역 아이디로 캠프 구역을 조회해 존재 여부 확인
		CampField campField    = campFieldJPARepository.findByFieldNameAndCampId
				(requestDTO.getFieldName(),requestDTO.getCampId());
		if(campField == null) {
			throw new Exception400("잘못된 캠프 구역이 입력되었습니다.");
		}
		// 요청 데이터와 계산한 결제 금액의 동일 여부 검사
		Period period = Period.between(LocalDate.parse(requestDTO.getCheckIn()), //예약 일수 계산
				LocalDate.parse(requestDTO.getCheckOut()));
		if(!requestDTO.getTotalPrice().equals(campField.getPrice() * period.getDays())) {
			throw new Exception400("결제 금액이 올바르지 않습니다.");
		}

	}
	
	// 결제 DB 등록
	public OrderRespDTO.PaymentWriteDTO paymentWrite(Integer userId,@Valid OrderReqDTO.PaymentWriteDTO requestDTO) {
		// requestDTO 가공
		Timestamp checkInDate  = TimestampUtils.convertToTimestamp(requestDTO.getCheckIn());
		Timestamp checkOutDate = TimestampUtils.convertToTimestamp(requestDTO.getCheckOut());
		CampField campField    = campFieldJPARepository.findByFieldNameAndCampId
						(requestDTO.getFieldName(),requestDTO.getCampId());
				
		// DB 입력
		try {
			orderJPARepository.save(Order.builder()
					.orderNumber(requestDTO.getOrderNumber())
					.checkInDate(checkInDate)
					.checkOutDate(checkOutDate)
					.user(User.builder().id(userId).build())
					.campField(campField)
					.build());
			return new OrderRespDTO.PaymentWriteDTO(campField.getCamp().getCampFieldImage());
		} catch (Exception e) {
			throw new Exception500("결제 등록에 실패했습니다");
		}
		
	}
	

	// 예약을 확인하여 환불할 예약 정보 조회
	public OrderRespDTO.RefundInfoDTO refundInfo(Integer userId, @Valid OrderReqDTO.RefundInfoDTO requestDTO) {
		//사용자가 없으면 예외 처리
		userJPARepository.findById(userId)
					.orElseThrow(() -> new Exception400("해당 사용자가 없습니다."));
		// orderId와 userId에 맞는 예약 정보가 있는지 확인
		Order order = orderJPARepository.findByIdAndUserId(requestDTO.getOrderId(), userId);
		if(order == null) {
			throw new Exception404("잘못된 예약번호입니다.");
		}
		// 환불할 예약 정보를 응답 데이터로 가공해 반환
		return new OrderRespDTO.RefundInfoDTO(CampRespDTO.getCampInfo(order.getCampField().getCamp()), order );
	}
	


}
