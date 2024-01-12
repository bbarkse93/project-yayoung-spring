package com.example.team_project.camp;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_project._core.erroes.exception.Exception404;
import com.example.team_project.camp._dto.CampReqDTO;
import com.example.team_project.camp._dto.CampRespDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampDetailDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampRatingDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampReviewDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampReviewListDTO;
import com.example.team_project.camp.camp_bookmark.CampBookmark;
import com.example.team_project.camp.camp_bookmark.CampBookmarkJPARepository;
import com.example.team_project.camp.camp_image.CampImageJPARepository;
import com.example.team_project.camp.camp_rating.CampRating;
import com.example.team_project.camp.camp_rating.CampRatingJPARepository;
import com.example.team_project.camp.camp_review.CampReview;
import com.example.team_project.camp.camp_review.CampReviewJPARepository;
import com.example.team_project.camp_field.CampFieldJPARepository;
import com.example.team_project.order.OrderJPARepository;
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
    private final CampFieldJPARepository campFieldJPARepository;
    private final OrderJPARepository orderJPARepository;

    // 사용자 캠핑장 목록 출력 기능(필터 적용 가능)
    public CampRespDTO.CampListDTO getAllCamps(CampReqDTO.CampListDTO requestDTO) {

    	List<Camp> camps = campJPARepository.findAll();
    	// 위치 필터
//        List<CampRespDTO.CampListDTO> responseDTO = camps.stream()
//                .map(c -> new CampRespDTO.CampListDTO(c))
//                .collect(Collectors.toList());
//        List<CampListDTO> campList = camps.stream().map(this::convertToCampRespDto).collect(Collectors.toList());
        return new CampRespDTO.CampListDTO(camps, requestDTO);

    }

    public CampDetailDTO getCampDetail(Integer campId) {
        Camp camp = campJPARepository.findById(campId)
                .orElseThrow(() -> new Exception404("해당 캠프장이 존재하지 않습니다."));
        long campCount = campReviewJPARepository.countByCampId(camp.getId());

        return new CampDetailDTO(camp, campCount);
    }

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

    // 전우진 캠핑장 리뷰 리스트 데이터 전달
    // 캠핑장의 총 평점과 세부 평점 정보를 조회하는 메서드
    public CampRatingDTO getCampRatingInfo(Integer campId) {
        // 캠핑장 평점 정보 조회
        List<CampRating> campRatings = campRatingJPARepository.findByCampId(campId);

        // 평점 계산 로직
        double cleanlinessAvg = campRatings.stream()
                .mapToDouble(CampRating::getCleanliness)
                .average()
                .orElse(0.0);
        double managementnessAvg = campRatings.stream()
                .mapToDouble(CampRating::getManagementness)
                .average()
                .orElse(0.0);
        double friendlinessAvg = campRatings.stream()
                .mapToDouble(CampRating::getFriendliness)
                .average()
                .orElse(0.0);
        double total = (cleanlinessAvg + managementnessAvg + friendlinessAvg) / 3.0;

        // DTO 생성 및 반환
        CampRatingDTO campRatingInfo = new CampRatingDTO(total, cleanlinessAvg, managementnessAvg, friendlinessAvg);
        return campRatingInfo;
    }

    // 캠핑장 리뷰 리스트를 조회하는 메서드
    public List<CampReviewListDTO> getCampReviewList(Integer campId) {
        // 캠핑장 리뷰 리스트 조회
        List<CampReview> reviews = campReviewJPARepository.findByCampId(campId);
        // DecimalFormat을 사용하여 소수점 첫째 자리까지 표시
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP); // 반올림 모드 설정

        // DTO 리스트 생성
        List<CampReviewListDTO> reviewListDTOs = reviews.stream()
                .map(review -> {
                    double totalRating = review.getCampRating().total();
                    String formattedTotal = decimalFormat.format(totalRating); // 소수점 첫째 자리까지 포맷
                    return CampReviewListDTO.builder()
                            .id(review.getId())
                            .userId(review.getUser().getId())
                            .campId(review.getCamp().getId())
                            .orderId(review.getOrder() != null ? review.getOrder().getId() : null)
                            .userImage(review.getUser().getUserImage())
                            .nickName(review.getUser().getNickname())
                            .createdAt(review.getCreatedAt())
                            .total(Double.parseDouble(formattedTotal)) // 포맷된 문자열을 다시 숫자로 변환
                            .content(review.getContent())
                            .build();
                })
                .collect(Collectors.toList());

        return reviewListDTOs;
    }

    // 리뷰 작성
    @Transactional
    public CampReview saveCampReview(CampReviewDTO campReviewDTO) {
        // 참조 엔티티들을 레포지토리에서 조회합니다.
        User user = userJPARepository.findById(campReviewDTO.getUserId())
                                      .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Camp camp = campJPARepository.findById(campReviewDTO.getCampId())
                                      .orElseThrow(() -> new EntityNotFoundException("Camp not found"));
        // Order 객체 조회 로직(필요한 경우)

        // CampRating 객체를 Builder를 통해 생성합니다.
        CampRating campRating = CampRating.builder()
                                          .cleanliness(campReviewDTO.getCleanliness())
                                          .managementness(campReviewDTO.getManagementness())
                                          .friendliness(campReviewDTO.getFriendliness())
                                          // 여기에 다른 필드가 필요하다면 추가합니다.
                                          .build();
        // CampRating 객체를 저장하거나 업데이트하는 로직이 필요합니다.

        // 새로운 리뷰 객체를 Builder를 통해 생성합니다.
        CampReview campReview = CampReview.builder()
                                          .content(campReviewDTO.getContent())
                                          // 이미지 처리 로직 후 이미지 경로 설정
                                          .reviewImage(null)
                                          .user(user)
                                          .camp(camp)
                                          .campRating(campRating)
                                          // createdAt은 자동으로 생성되므로 설정할 필요가 없습니다.
                                          .build();

        // 리뷰 객체를 데이터베이스에 저장합니다.
        return campReviewJPARepository.save(campReview);
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
