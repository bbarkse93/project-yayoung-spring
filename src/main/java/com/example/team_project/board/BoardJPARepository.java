package com.example.team_project.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {
    List<Board> findByBoardCategoryId(Integer boardCategoryId);
}
