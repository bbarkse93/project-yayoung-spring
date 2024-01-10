package com.example.team_project.camp.camp_image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CampImageJPARepository extends JpaRepository<CampImage, Integer> {

    List<CampImage> findByCampId(Integer campId);
    
    // CampImage findByCampId(Integer campId);
}
