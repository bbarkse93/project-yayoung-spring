package com.example.team_project.admin.banner;

import com.example.team_project.board.Board;
import com.example.team_project.camp.camp_bookmark.CampBookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BannerJPARepository extends JpaRepository<Banner, Integer> {

    @Query(value = "SELECT * FROM banner_tb", nativeQuery = true)
    Page<Banner> mfindSearchPageAll(Pageable pageable);

    @Query(value = "SELECT * FROM banner_tb", nativeQuery = true)
    List<Banner> mfindSearchAll();
}
