package com.example.team_project.board;

import com.example.team_project.camp.Camp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {
    List<Board> findByBoardCategoryId(Integer boardCategoryId);

    @Query(value = "SELECT * FROM board_tb WHERE (title LIKE CONCAT('%', :keyword ,'%') or content LIKE CONCAT('%', :keyword ,'%')) and (:categoryId is null or board_category_id = :categoryId)", nativeQuery = true)

    Page<Board> mfindSearchPageAll(@Param("keyword") String keyword, Pageable pageable, @Param("categoryId") Integer categoryId);

    @Query(value = "SELECT * FROM board_tb WHERE (title LIKE CONCAT('%', :keyword ,'%') or content LIKE CONCAT('%', :keyword ,'%'))", nativeQuery = true)
    List<Board> mfindSearchAll(@Param("keyword") String keyword);
}
