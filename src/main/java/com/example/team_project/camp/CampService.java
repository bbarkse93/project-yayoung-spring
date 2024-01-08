package com.example.team_project.camp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_project._core.erroes.exception.Exception404;
import com.example.team_project.camp._dto.CampReqDTO;
import com.example.team_project.camp._dto.CampRespDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampListDTO;
import com.example.team_project.camp.camp_bookmark.CampBookmark;
import com.example.team_project.camp.camp_bookmark.CampBookmarkJPARepository;
import com.example.team_project.camp.camp_image.CampImageJPARepository;
import com.example.team_project.camp.camp_rating.CampRatingJPARepository;
import com.example.team_project.camp.camp_review.CampReview;
import com.example.team_project.camp.camp_review.CampReviewJPARepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class CampService {

    private final CampJPARepository campJPARepository;

    public List<CampListDTO> getAllCamps() {
        List<Camp> camps = campJPARepository.findAll();
        return camps.stream().map(this::convertToCampRespDto).collect(Collectors.toList());

    }

    private CampListDTO convertToCampRespDto(Camp camp) {
        // Camp 엔티티 객체를 받아서 CampRespDTO 객체로 변환하는 로직
        // 필요한 필드를 매핑하고 DTO 객체를 반환
        return new CampListDTO(
                camp.getId(),
                camp.getCampName(),
                camp.getCampAddress(),
                camp.getCampCallNumber(),
                camp.getCampWebsite(),
                camp.getCampRefundPolicy(),
                camp.isCampWater(),
                camp.isCampGarbageBag(),
                camp.getHoliday(),
                camp.getCampCheckIn(),
                camp.getCampCheckOut(),
                camp.getCampFieldImage()); 
            }


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
