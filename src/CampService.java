package com.example.team_project.camp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_project._core.errors.exception.Exception404;
import com.example.team_project.camp._dto.CampReqDTO;
import com.example.team_project.camp._dto.CampReqDTO.CampBookmarkDTO;
import com.example.team_project.camp._dto.CampRespDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampDetailDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampListDTO;
import com.example.team_project.camp.camp_bookmark.CampBookmark;
import com.example.team_project.camp.camp_bookmark.CampBookmarkJPARepository;
import com.example.team_project.camp.camp_image.CampImage;
import com.example.team_project.camp.camp_image.CampImageJPARepository;
import com.example.team_project.camp.camp_rating.CampRatingJPARepository;
import com.example.team_project.camp.camp_review.CampReview;
import com.example.team_project.camp.camp_review.CampReviewJPARepository;
import com.example.team_project.user.User;
import com.example.team_project.user.UserJPARepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class CampService {

    private final CampJPARepository campJPARepository;
    private final UserJPARepository userJPARepository;
    private final CampBookmarkJPARepository campBookmarkJPARepository;
    private final CampImageJPARepository campImageJPARepository;
    private final CampRatingJPARepository campRatingJPARepository;
    private final CampReviewJPARepository campReviewJPARepository;

    // 사용자 캠핑장 목록 출력 기능
    public CampRespDTO.CampListDTO getAllCamps() {
        List<Camp> camps = campJPARepository.findAll();

        // List<CampListDTO> campList =
        // camps.stream().map(this::convertToCampRespDto).collect(Collectors.toList());
        return new CampRespDTO.CampListDTO(camps) ;
    }

    // private CampListDTO convertToCampRespDto(Camp camp) {
    //     // Camp 엔티티 객체를 받아서 CampRespDTO 객체로 변환하는 로직
    //     // 필요한 필드를 매핑하고 DTO 객체를 반환
    //     return new CampListDTO(camp);
    // }

    // 북마크 추가
    public CampBookmark addBookmark(Integer userId, CampReqDTO.CampBookmarkDTO dto) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Camp camp = campJPARepository.findById(dto.getCampId())
                .orElseThrow(() -> new EntityNotFoundException("Camp not found"));

        CampBookmark campBookmark = CampBookmark.builder()
                .user(User.builder().id(user.getId()).build())
                .camp(Camp.builder().id(camp.getId()).build())
                .build();

        CampBookmark response = campBookmarkJPARepository.save(campBookmark);

        return response;
    }

    // 북마크 해제
    public void removeBookmark(Integer userId, Integer campId) {
        List<CampBookmark> bookmarks = campBookmarkJPARepository.findByUserId(userId);
        bookmarks.stream()
                .filter(bookmark -> bookmark.getCamp().getId().equals(campId))
                .findFirst()
                .ifPresent(campBookmarkJPARepository::delete);
    }

    // 사용자의 관심 캠핑장 조회 기능
    public List<CampBookmark> getUserBookmarks(Integer userId) {
        return campBookmarkJPARepository.findByUserId(userId);
    }

    // 사용자 캠핑장 상세 페이지 이미지 슬라이더
    public CampDetailDTO getCampDetails(Integer campId) {
        Camp camp = campJPARepository.findById(campId)
                .orElseThrow(() -> new EntityNotFoundException("Camp not found"));

        List<CampImage> images = campImageJPARepository.findByCampId(campId);
        List<String> imageUrls = images.stream().map(CampImage::getCampImage).collect(Collectors.toList());

        // CampDetailDTO 생성 및 반환
        return new CampDetailDTO(camp, imageUrls);
    }

    // ME 관심캠핑장 목록 페이지 요청
    public CampRespDTO.CampBookMarkListDTO campBookMarkPage(Integer userId) {
        List<CampBookmark> campBookmarkList = campBookmarkJPARepository.findByUserId(userId);
        for (CampBookmark campBookmark : campBookmarkList) {
            System.out.println("campBookmark : " + campBookmark.getCamp().getId());
        }
        return new CampRespDTO.CampBookMarkListDTO(campBookmarkList);
    }

    // 내 캠핑장 연도별 목록 조회
    public CampRespDTO.MyCampListDTO myCampFieldList(Integer userId, CampReqDTO.MyCampListDTO requestDTO) {
        List<CampReview> campReviews = campReviewJPARepository.findAllByUserId(userId);
        if (campReviews == null)
            throw new Exception404("작성하신 리뷰가 없습니다");
        return new CampRespDTO.MyCampListDTO(campReviews, requestDTO.getYear());
    }
}
