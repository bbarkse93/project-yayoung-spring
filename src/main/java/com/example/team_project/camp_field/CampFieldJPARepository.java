package com.example.team_project.camp_field;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CampFieldJPARepository extends JpaRepository<CampField, Integer> {

	List<CampField> findAllByCampId(Integer campId);

	CampField findByFieldNameAndCampId(String fieldName, Integer campId);
}
