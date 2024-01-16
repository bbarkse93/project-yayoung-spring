package com.example.team_project.notice;

import com.example.team_project.camp.Camp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeJPARepository extends JpaRepository<Notice, Integer> {
    @Query(value = "SELECT * FROM notice_tb WHERE title LIKE CONCAT('%', :keyword ,'%') or content LIKE CONCAT('%', :keyword ,'%')", nativeQuery = true)
    Page<Notice> mfindSearchPageAll(String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM notice_tb WHERE title LIKE CONCAT('%', :keyword ,'%') or content LIKE CONCAT('%', :keyword ,'%')", nativeQuery = true)
    List<Notice> mfindSearchAll(@Param("keyword") String keyword);
}
