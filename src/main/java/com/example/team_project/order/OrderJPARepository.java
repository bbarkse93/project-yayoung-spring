package com.example.team_project.order;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.example.team_project.camp.Camp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderJPARepository extends JpaRepository<Order, Integer> {
	// 아이디로 다가오는 여행 일정 조회
	Order findFirstByUserIdAndCheckInDateAfterOrderByCheckInDateAsc(Integer userId, Timestamp today);
	// 아이디로 캠핑 일정 목록 조회
	List<Order> findAllByUserIdAndCheckInDateAfterOrderByCheckInDateAsc(Integer userId, Timestamp today);
	// 캠핑 일정 목록 조회
	List<Order> findAllByCheckInDateAfterOrderByCheckInDateAsc(Timestamp today);


	// admin 검색
	String sql = "SELECT o.* " +
			"FROM order_tb AS o " +
			"INNER JOIN camp_field_tb AS cf ON o.camp_field_id = cf.id " +
			"INNER JOIN user_tb AS u ON u.id = o.user_id " +
			"INNER JOIN camp_tb AS c ON c.id = cf.camp_id " +
			"WHERE o.is_refund = true " +
			"AND (u.nickname LIKE CONCAT('%', :keyword ,'%') OR c.camp_name LIKE CONCAT('%', :keyword ,'%'))";

	@Query(value = sql, nativeQuery = true)
	Page<Order> mfindSearchPageAll(String keyword, Pageable pageable);

	@Query(value = sql, nativeQuery = true)
	List<Order> mfindSearchAll(@Param("keyword") String keyword);
	
	Order findByIdAndUserId(Integer orderId, Integer userId);
	// 과거 캠핑 목록(내 캠핑장) 조회
	List<Order> findAllByUserIdAndCheckInDateBeforeOrderByCheckInDateAsc(Integer userId, Timestamp findCurrnetTime);
	List<Order> findAllByUserId(Integer id);
	List<Order> findAllByUserIdAndCheckOutDateBeforeOrderByCheckOutDateAsc(Integer userId, Timestamp findCurrnetTime);

	
	

}
