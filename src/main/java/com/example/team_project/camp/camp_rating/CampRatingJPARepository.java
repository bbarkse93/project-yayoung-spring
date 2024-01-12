package com.example.team_project.camp.camp_rating;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CampRatingJPARepository extends JpaRepository<CampRating, Integer> {
    List<CampRating> findByCampId(Integer campId);
}
