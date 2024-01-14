package com.example.team_project.camp.camp_review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CampReviewJPARepository extends JpaRepository<CampReview, Integer> {

	List<CampReview> findAllByUserId(Integer userId);

    long countByCampId(Integer campId);

    List<CampReview> findAllByCampId(Integer campId);
}
