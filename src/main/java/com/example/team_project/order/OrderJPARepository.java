package com.example.team_project.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJPARepository extends JpaRepository<Order, Integer> {
	// 아이디로 다가오는 여행 일정 조회
	Order findFirstByUserIdOrderByCheckInDateAsc(Integer userId);
	// 아이디로 캠핑 일정 목록 조회
	List<Order> findAllByUserId(int userId);
	
}
