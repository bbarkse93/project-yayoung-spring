package com.example.team_project.option_management;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionManagementJPARepository extends JpaRepository<OptionManagement, Integer> {

    List<OptionManagement> findAllByCampId(Integer campId);

    void deleteByOptionId(Integer optionId);
}
