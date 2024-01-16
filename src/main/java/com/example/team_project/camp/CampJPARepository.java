package com.example.team_project.camp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampJPARepository extends JpaRepository<Camp, Integer> {

    @Query(value = "SELECT * FROM camp_tb WHERE camp_name LIKE CONCAT('%', :keyword ,'%') or camp_address LIKE CONCAT('%', :keyword ,'%')", nativeQuery = true)
    Page<Camp> mfindSearchPageAll(String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM camp_tb WHERE camp_name LIKE CONCAT('%', :keyword ,'%') or camp_address LIKE CONCAT('%', :keyword ,'%')", nativeQuery = true)
    List<Camp> mfindSearchAll(@Param("keyword") String keyword);


}
