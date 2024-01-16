package com.example.team_project.user;

import com.example.team_project.camp.Camp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserJPARepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user_tb WHERE (username LIKE CONCAT('%', :keyword ,'%') or nickname LIKE CONCAT('%', :keyword ,'%')) and role = true", nativeQuery = true)
    Page<User> mfindSearchPageAll(String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM user_tb WHERE (username LIKE CONCAT('%', :keyword ,'%') or nickname LIKE CONCAT('%', :keyword ,'%')) and role = true", nativeQuery = true)
    List<User> mfindSearchAll(@Param("keyword") String keyword);

    User findByUsername(String username);
}
