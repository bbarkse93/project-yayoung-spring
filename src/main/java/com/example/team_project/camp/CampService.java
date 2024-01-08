package com.example.team_project.camp;

import com.example.team_project._core.erroes.exception.Exception404;
import com.example.team_project.camp._dto.CampReqDTO.MyCampListDTO;
import com.example.team_project.camp._dto.CampReqDTO;
import com.example.team_project.camp._dto.CampRespDTO;
import com.example.team_project.camp.camp_bookmark.CampBookmark;
import com.example.team_project.camp.camp_bookmark.CampBookmarkJPARepository;
import com.example.team_project.camp.camp_image.CampImage;
import com.example.team_project.camp.camp_image.CampImageJPARepository;
import com.example.team_project.camp.camp_rating.CampRating;
import com.example.team_project.camp.camp_rating.CampRatingJPARepository;
import com.example.team_project.camp.camp_review.CampReview;
import com.example.team_project.camp.camp_review.CampReviewJPARepository;
import com.example.team_project.order.Order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CampService {

    private final CampJPARepository campJPARepository;
    private final CampBookmarkJPARepository campBookmarkJPARepository;
    private final CampImageJPARepository campImageJPARepository;
    private final CampRatingJPARepository campRatingJPARepository;
    private final CampReviewJPARepository campReviewJPARepository;


    // ME 관심캠핑장 목록 페이지 요청
    public CampRespDTO.CampBookMarkListDTO campBookMarkPage(Integer userId) {
        List<CampBookmark> campBookmarkList = campBookmarkJPARepository.findByUserId(userId);
        for (CampBookmark campBookmark:campBookmarkList) {
            System.out.println("campBookmark : " + campBookmark.getCamp().getId());
        }
        return new CampRespDTO.CampBookMarkListDTO(campBookmarkList);
    }

    //내 캠핑장 연도별 목록 조회
	public CampRespDTO.MyCampListDTO myCampFieldList(Integer userId, CampReqDTO.MyCampListDTO requestDTO) {
		List<CampReview> campReviews = campReviewJPARepository.findAllByUserId(userId);
		if(campReviews == null) throw new Exception404("작성하신 리뷰가 없습니다");
		return new CampRespDTO.MyCampListDTO(campReviews, requestDTO.getYear());
	}
}
