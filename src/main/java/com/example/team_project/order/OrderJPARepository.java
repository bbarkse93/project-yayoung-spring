package com.example.team_project.order;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJPARepository extends JpaRepository<Order, Integer> {
	// 아이디로 다가오는 여행 일정 조회
	Order findFirstByUserIdAndCheckInDateAfterOrderByCheckInDateAsc(Integer userId, Timestamp today);
	// 아이디로 캠핑 일정 목록 조회
	List<Order> findAllByUserIdAndCheckInDateAfterOrderByCheckInDateAsc(Integer userId, Timestamp today);
	List<Order> findAllByCheckInDateAfterOrderByCheckInDateAsc(Timestamp today);
	
	
}
