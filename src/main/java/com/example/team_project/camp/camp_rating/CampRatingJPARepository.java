package com.example.team_project.camp.camp_rating;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampRatingJPARepository extends JpaRepository<CampRating, Integer> {
    List<CampRating> findByCampId(Integer campId);
}
