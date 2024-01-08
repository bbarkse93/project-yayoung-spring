package com.example.team_project.camp.camp_bookmark;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampBookmarkJPARepository extends JpaRepository<CampBookmark, Integer> {
    List<CampBookmark> findByUserId(Integer userId);
}
