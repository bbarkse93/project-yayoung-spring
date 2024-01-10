package com.example.team_project.camp.camp_bookmark;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.team_project.camp._dto.CampReqDTO.CampBookmarkDTO;

import java.util.List;

public interface CampBookmarkJPARepository extends JpaRepository<CampBookmark, Integer> {
    List<CampBookmark> findByUserId(Integer userId);
}
