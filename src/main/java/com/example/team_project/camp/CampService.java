package com.example.team_project.camp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_project.camp._dto.CampRespDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampListDTO;
import com.example.team_project.camp.camp_bookmark.CampBookmark;
import com.example.team_project.camp.camp_bookmark.CampBookmarkJPARepository;
import com.example.team_project.camp.camp_image.CampImageJPARepository;
import com.example.team_project.camp.camp_rating.CampRatingJPARepository;
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

    // 사용자 캠핑장 목록 출력 기능
    public List<CampListDTO> getAllCamps() {
        List<Camp> camps = campJPARepository.findAll();
        List<CampListDTO> campList = camps.stream().map(this::convertToCampRespDto).collect(Collectors.toList());
        return campList;

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

    // 사용자 관심 캠핑장 등록 기능
    public CampBookmark addBookmark(Integer userId, Integer campId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Camp camp = campJPARepository.findById(campId)
                .orElseThrow(() -> new EntityNotFoundException("Camp not found"));

        CampBookmark campBookmark = CampBookmark.builder()
                .user(user)
                .camp(camp)
                .build();
        return campBookmarkJPARepository.save(campBookmark);
    }

    // 관심 캠핑장 등록 후 버튼 재클릭 시 해제 기능
    public void bookmarkRemove(Integer userId, Integer campId) {
        List<CampBookmark> bookmarks = campBookmarkJPARepository.findByUserId(userId);
        for (CampBookmark bookmark : bookmarks) {
            if (bookmark.getCamp().getId().equals(campId)) {
                campBookmarkJPARepository.delete(bookmark);
                break;
            }
        }
    }

    // 사용자의 관심 캠핑장 조회 기능
    public List<CampBookmark> getUserBookmarks(Integer userId) {
        return campBookmarkJPARepository.findByUserId(userId);
    }

    private final CampBookmarkJPARepository campBookmarkJPARepository;
    private final CampImageJPARepository campImageJPARepository;
    private final CampRatingJPARepository campRatingJPARepository;

    // ME 관심캠핑장 목록 페이지 요청
    public CampRespDTO.CampBookMarkListDTO campBookMarkPage(Integer userId) {
        List<CampBookmark> campBookmarkList = campBookmarkJPARepository.findByUserId(userId);
        for (CampBookmark campBookmark : campBookmarkList) {
            System.out.println("campBookmark : " + campBookmark.getCamp().getId());
        }
        return new CampRespDTO.CampBookMarkListDTO(campBookmarkList);
    }
}
