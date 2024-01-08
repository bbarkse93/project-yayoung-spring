package com.example.team_project.camp.camp_image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CampImageJPARepository extends JpaRepository<CampImage, Integer> {

    CampImage findByCampId(Integer campId);
}
