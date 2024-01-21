package com.example.team_project.camp;

import java.util.List;
import java.util.stream.Collectors;

import com.example.team_project._core.errors.exception.Exception400;
import com.example.team_project.camp.camp_rating.CampRating;
import com.example.team_project.camp.camp_review.CampReview;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_project._core.errors.exception.Exception404;
import com.example.team_project._core.utils.TimestampUtils;
import com.example.team_project.camp._dto.CampReqDTO;
import com.example.team_project.camp._dto.CampReqDTO.CampBookmarkDeleteDTO;
import com.example.team_project.camp._dto.CampRespDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampDetailDTO;
import com.example.team_project.camp.camp_bookmark.CampBookmark;
import com.example.team_project.camp.camp_bookmark.CampBookmarkJPARepository;
import com.example.team_project.camp.camp_image.CampImageJPARepository;
import com.example.team_project.camp.camp_rating.CampRatingJPARepository;
import com.example.team_project.camp.camp_review.CampReviewJPARepository;
import com.example.team_project.camp_field.CampFieldJPARepository;
import com.example.team_project.order.Order;
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
        return new CampRespDTO.CampListDTO(camps, requestDTO);
    }

    // 캠핑장 상세 보기
    public CampDetailDTO getCampDetail(Integer campId, Integer userId) {
        Camp camp = campJPARepository.findById(campId)
                .orElseThrow(() -> new Exception404("해당 캠프장이 존재하지 않습니다."));
        long campCount = campReviewJPARepository.countByCampId(camp.getId());
        CampBookmark campBookmark = campBookmarkJPARepository.findByCampIdAndUserId(campId, userId);
        boolean isBookmark = false;
        if(campBookmark != null){
            isBookmark = true;
        }
        return new CampDetailDTO(camp, campCount, isBookmark);
    }

    // 북마크 추가
    @Transactional
    public CampRespDTO.BookmarkStateDTO addBookmark(Integer userId, CampReqDTO.CampBookmarkDTO dto) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Camp camp = campJPARepository.findById(dto.getCampId())
                .orElseThrow(() -> new EntityNotFoundException("Camp not found"));

        CampBookmark campBookmark = CampBookmark.builder()
                .user(User.builder().id(user.getId()).build())
                .camp(Camp.builder().id(camp.getId()).build())
                .build();

        CampBookmark bookmark = campBookmarkJPARepository.save(campBookmark);
        boolean isBookmark = false;
        if(bookmark != null){
            isBookmark = true;
        }

        return new CampRespDTO.BookmarkStateDTO(isBookmark);
    }

    // 북마크 해제
    @Transactional
    public CampRespDTO.BookmarkStateDTO removeBookmark(Integer userId, CampBookmarkDeleteDTO requestDTO) {
        CampBookmark campBookmark = campBookmarkJPARepository.findByCampIdAndUserId(requestDTO.getCampId(), userId);
        boolean isBookmark = true;
        try{
            campBookmarkJPARepository.deleteById(campBookmark.getId());
            isBookmark = false;
        }catch (Exception e){
            throw new Exception400("북마크 삭제에 실패했습니다.");
        }
        return new CampRespDTO.BookmarkStateDTO(isBookmark);
//        List<CampBookmark> bookmarks = campBookmarkJPARepository.findByUserId(userId);
//        bookmarks.stream()
//                .filter(bookmark -> bookmark.getCamp().getId().equals(requestDTO.getCampId()))
//                .findFirst()
//                .ifPresent(campBookmarkJPARepository::delete);
    }


    // 사용자의 관심 캠핑장 조회 기능
    public List<CampBookmark> bookmarkList(Integer userId) {
        return campBookmarkJPARepository.findByUserId(userId);
    }


    // ME 관심캠핑장 목록 페이지 요청
    public CampRespDTO.CampBookMarkListDTO campBookMarkPage(Integer userId) {
        List<CampBookmark> campBookmarkList = campBookmarkJPARepository.findByUserId(userId);
        return new CampRespDTO.CampBookMarkListDTO(campBookmarkList);
    }

    // 내 캠핑장 연도별 목록 조회
    public CampRespDTO.MyCampListDTO myCampList(Integer userId, CampReqDTO.MyCampListDTO requestDTO) {
        List<Order> orders = orderJPARepository.findAllByUserIdAndIsRefundAndCheckInDateBeforeOrderByCheckInDateAsc(userId, false ,TimestampUtils.findCurrnetTime());
        return new CampRespDTO.MyCampListDTO(orders, requestDTO.getYear());
    }

    // 캠핑장 검색
    public CampRespDTO.SearchCampDTO searchCamp(String keyword) {
        System.out.println("serviceKeyword는? " + keyword);
        List<Camp> campList = campJPARepository.mfindSearchAll(keyword);
        System.out.println("결과는? " + campList.size());
        return new CampRespDTO.SearchCampDTO(campList);
    }
    
    // 리뷰 등록
    public CampRespDTO.AddCampReviewDTO addReview(CampReqDTO.CampReviewDTO requestDTO) {
    	
    	// 리뷰 작성 조건 충족 여부 검사(캠핑을 다녀와야 리뷰 작성 가능)
    	List<Order> orders = orderJPARepository.findAllByUserIdAndIsRefundAndCheckOutDateBeforeOrderByCheckOutDateAsc(requestDTO.getUserId(), false ,TimestampUtils.findCurrnetTime());
    	orders = orders.stream().filter(order -> order != null && order.getCampField().getCamp().getId().equals(requestDTO.getCampId())).collect(Collectors.toList());
    	if(orders == null || orders.size() == 0) {
    		throw new Exception400("다녀간 캠핑장이 없습니다.");
    	}
    	List<CampReview> campReviews = campReviewJPARepository.findAllByUserIdAndCampId(requestDTO.getUserId(),requestDTO.getCampId());
    	// 예약으로 다녀간 회수와 리뷰 수가 일치하면 리뷰 작성 금지
    	if(orders.size() == campReviews.size()) {
    		throw new Exception400("이미 리뷰를 작성하셨습니다.");
    	}
    	
        CampRating campRating = CampRating.builder()
                .cleanliness(requestDTO.getCleanliness())
                .managementness(requestDTO.getManagementness())
                .friendliness(requestDTO.getFriendliness())
                .user(User.builder().id(requestDTO.getUserId()).build())
                .camp(Camp.builder().id(requestDTO.getCampId()).build())
                .build();
        // 별점 인서트
        CampRating rating = campRatingJPARepository.save(campRating);
        CampReview campReview = CampReview.builder()
                .content(requestDTO.getContent())
                .user(User.builder().id(requestDTO.getUserId()).build())
                .camp(Camp.builder().id(requestDTO.getCampId()).build())
                .campRating(rating)
                .build();
        // 리뷰 인서트
        CampReview review = campReviewJPARepository.save(campReview);

        return new CampRespDTO.AddCampReviewDTO(review, rating);
    }

    // 리뷰 목록 조회
    public CampRespDTO.CampReviewListDTO campReviewList(Integer campId) {
        List<CampReview> campReviewList = campReviewJPARepository.findAllByCampId(campId, Sort.by(Sort.Direction.DESC, "createdAt"));
        long campReviewCount = campReviewJPARepository.countByCampId(campId);
        return new CampRespDTO.CampReviewListDTO(campReviewList, campReviewCount);
    }
}
