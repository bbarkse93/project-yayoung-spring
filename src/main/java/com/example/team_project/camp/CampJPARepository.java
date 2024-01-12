package com.example.team_project.camp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CampJPARepository extends JpaRepository<Camp, Integer> {
	@Query("SELECT ca FROM Camp ca "
			+ "JOIN ca.optionManagementList om "
			+ "JOIN om.option op "
			+ "JOIN op.optionCategory oc "
			+ "WHERE op.optionName IN :optionNames")
	List<Camp> mFindFilteredAll(@Param("optionNames") List<String> optionNames);

}
