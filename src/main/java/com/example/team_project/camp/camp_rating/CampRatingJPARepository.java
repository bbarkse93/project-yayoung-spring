package com.example.team_project.camp.camp_rating;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CampRatingJPARepository extends JpaRepository<CampRating, Integer> {
    CampRating findByCampId(Integer campId);
}
