package com.example.team_project.admin;

import com.example.team_project._core.errors.exception.Exception404;
import com.example.team_project.admin._dto.AdminRespDTO;
import com.example.team_project.board.BoardJPARepository;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.CampJPARepository;
import com.example.team_project.camp.camp_review.CampReview;
import com.example.team_project.camp.camp_review.CampReviewJPARepository;
import com.example.team_project.notice.NoticeJPARepository;
import com.example.team_project.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class AdminService {

    private final CampJPARepository campJPARepository;
    private final CampReviewJPARepository campReviewJPARepository;
    private final UserJPARepository userJPARepository;
    private final BoardJPARepository boardJPARepository;
    private final NoticeJPARepository noticeJPARepository;

    // 캠핑장 목록(캠핑장 수)
    public List<AdminRespDTO.CampDTO> campList(String keyword) {
        List<Camp> campList = campJPARepository.mfindSearchAll(keyword);
        return campList.stream().map(AdminRespDTO.CampDTO::new).collect(Collectors.toList());
    }

    // 캠핑장 목록 페이징(페이징 된 화면 수)
    public List<AdminRespDTO.CampDTO> campSearch(Integer page, String keyword, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
        Page<Camp> campPG = campJPARepository.mfindSearchPageAll(keyword, pageable);
        return campPG.stream().map(AdminRespDTO.CampDTO::new).collect(Collectors.toList());
    }

    // 캠핑장 삭제
    @Transactional
    public String deleteCamp(Integer campId) {
        Camp camp = campJPARepository.findById(campId).orElseThrow(() -> new Exception404("해당 캠핑장을 찾을 수 없습니다." + campId));
        camp.updateIsDelete(true);
        return "삭제에 성공했습니다.";
    }

    // 캠핑장 리뷰 리스트(모달)
    public AdminRespDTO.CampReviewDTO campReviewList(Integer campId) {
        Camp camp = campJPARepository.findById(campId).orElseThrow(() -> new Exception404("해당 캠핑장을 찾을 수 없습니다." + campId));
        List<CampReview> campReviewList = campReviewJPARepository.findAllByCampId(campId);
        return new AdminRespDTO.CampReviewDTO(camp, campReviewList);
    }

    // 캠핑장 현황 목록(캠핑장 수)
    public List<AdminRespDTO.RatingCampDTO> ratingCampList(String keyword) {
        List<Camp> campList = campJPARepository.mfindSearchAll(keyword);
        return campList.stream().map(AdminRespDTO.RatingCampDTO::new).collect(Collectors.toList());
    }

    // 캠핑장 현황 목록 페이징(페이징 된 화면 수)
    public List<AdminRespDTO.RatingCampDTO> ratingCampSearch(Integer page, String keyword, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
        Page<Camp> campPG = campJPARepository.mfindSearchPageAll(keyword, pageable);
        return campPG.stream().map(AdminRespDTO.RatingCampDTO::new).collect(Collectors.toList());
    }

    // 캠핑장 리뷰 삭제
    @Transactional
    public String deleteReview(Integer reviewId) {
        CampReview campReview = campReviewJPARepository.findById(reviewId).orElseThrow(() -> new Exception404("해당 리뷰를 찾을 수 없습니다." + reviewId));
        campReviewJPARepository.deleteById(campReview.getId());
        return "삭제에 성공했습니다.";
    }
}