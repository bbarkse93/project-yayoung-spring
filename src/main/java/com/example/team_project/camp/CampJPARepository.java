package com.example.team_project.camp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CampJPARepository extends JpaRepository<Camp, Integer> {
//	@Query("SELECT ca\r\n"
//			+ "FROM Camp ca\r\n"
//			+ "JOIN ca.optionManagements om\r\n"
//			+ "JOIN om.option op\r\n"
//			+ "JOIN op.optionCategory oc\r\n"
//			+ "WHERE op.optionName IN (:optionNames OR :optionNames IS EMPTY)")
//	List<Camp> mFindFilteredAll(List<String> optionNames);

}
