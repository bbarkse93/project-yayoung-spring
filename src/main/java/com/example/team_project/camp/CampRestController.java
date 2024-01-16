package com.example.team_project.camp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.team_project._core.utils.ApiUtils;
import com.example.team_project.camp._dto.CampReqDTO;
import com.example.team_project.camp._dto.CampRespDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampDetailDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampRatingDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampReviewDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampReviewListDTO;
import com.example.team_project.camp.camp_bookmark.CampBookmark;
import com.example.team_project.camp.camp_review.CampReview;
import com.example.team_project.user.User;
import com.example.team_project.user.UserService;

import jakarta.persistence.EntityNotFoundException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;;

@RequestMapping("/camp")
@RequiredArgsConstructor
@RestController
public class CampRestController {

    private final CampService campService;

    @Autowired
    private final UserService userService;

//    @GetMapping("/list")
//    public ResponseEntity<?> getAllCamps() {
//        // 인증검사
//        try {
//            // 토큰 검증 및 userId 추출
//            // @RequestHeader("Authorization") String token
//            // DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
//            // Integer userId = decodedJWT.getClaim("id").asInt();
//
//            // userJPARepository.findById(userId)
//            // .orElseThrow(() => new EntityNotFoundException("User not found"));
//
//            // 핵심로직
//            CampRespDTO.CampListDTO responseDTO = campService.getAllCamps();
//            return ResponseEntity.ok(ApiUtils.success(responseDTO));
//        } catch (JWTVerificationException | EntityNotFoundException e) {
//            // 인증 실패 혹은 사용자 미발견시 처리
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(ApiUtils.error(e.getMessage(), HttpStatus.UNAUTHORIZED));
//        }
//
//    }
    //캠핑장 목록 조회(필터 적용 가능)
    @GetMapping("/list")
    public ResponseEntity<?> getAllCamps(@ModelAttribute CampReqDTO.CampListDTO requestDTO) {
        // 인증검사

        // 핵심로직
        CampRespDTO.CampListDTO responseDTO = campService.getAllCamps(requestDTO);
        return ResponseEntity.ok(ApiUtils.success(responseDTO));
    }


    // 캠핑장 상세정보 페이지
     @GetMapping("/{id}")
     public ResponseEntity<?> getCampDetail(@PathVariable Integer id) {
         CampDetailDTO camp = campService.getCampDetail(id);

         return ResponseEntity.ok(ApiUtils.success(camp));
     }

    // 관심 캠핑장 등록 기능ㅈㅇㅈ
    @PostMapping("/bookmark")
    public ResponseEntity<?> addBookmark(
            @Valid CampReqDTO.CampBookmarkDTO requestDTO
    // , @RequestHeader("Authorization") String token
    ) {
        // 토큰 처리를 위한 서비스를 통해 userId 추출
        // DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        // Integer userId = decodedJWT.getClaim("id").asInt();
        System.out.println("컨트롤러");
        campService.addBookmark(1, requestDTO);

        // OrderRespDTO.myCampFieldListDTO responseDTO =
        // orderService.myCampFieldList(userId, requestDTO);
        return ResponseEntity.ok(ApiUtils.success("북마크 등록 완료"));
    }

    // 관심 캠핑장 등록 해제 기능ㅈㅇㅈ
    @DeleteMapping("/bookmark")
    public ResponseEntity<?> removeBookmark(@RequestParam Integer campId) {
        // 현재는 userId를 하드코딩으로 처리
        Integer userId = 1;

        campService.removeBookmark(userId, campId);
        return ResponseEntity.ok(ApiUtils.success("북마크 해제 완료"));
    }

    // 관심 캠핑장 목록 조회 엔드포인트
    @GetMapping("/bookmarks/{userId}")
    public ResponseEntity<?> getUserBookmarks(@PathVariable Integer userId) {
        List<CampBookmark> bookmarks = campService.getUserBookmarks(userId);
        return ResponseEntity.ok(bookmarks);
    }

    // 캠핑장 상세 정보 이미지 데이터 제공
//    @GetMapping("/{campId}/details")
//    public ResponseEntity<?> getCampDetails(@PathVariable Integer campId) {
//        CampDetailDTO campDetails = campService.getCampDetails(campId);
//        return ResponseEntity.ok(campDetails);
//    }

    // 240111 전우진
    // 캠핑장 리뷰 정보 조회
    @GetMapping("/{campId}/reviews")
    public ResponseEntity<?> getCampReviews(@PathVariable Integer campId) {
        // 캠핑장의 총 평점과 세부 평점 정보 조회
        CampRatingDTO campRatingInfo = campService.getCampRatingInfo(campId);
        // 캠핑장 리뷰 리스트 조회
        List<CampReviewListDTO> campReviewList = campService.getCampReviewList(campId);

        // 상단부 평점 정보와 하단부 리뷰 리스트를 한 객체에 담아 반환
        CampReviewsResponse response = new CampReviewsResponse(campRatingInfo, campReviewList);
        return ResponseEntity.ok(ApiUtils.success(response));
    }

    // 캠핑장 리뷰 정보를 담을 Response 객체
    class CampReviewsResponse {
        private CampRatingDTO campRating;
        private List<CampReviewListDTO> reviewList;

        // 생성자를 통해 필드를 초기화합니다.
        public CampReviewsResponse(CampRatingDTO campRatingInfo,
                List<CampReviewListDTO> campReviewList) {
            this.campRating = campRatingInfo;
            this.reviewList = campReviewList;
        }

        // 각 필드에 대한 getter 메서드
        public CampRatingDTO getCampRating() {
            return campRating;
        }

        public List<CampReviewListDTO> getReviewList() {
            return reviewList;
        }

        // 필요한 경우, 각 필드에 대한 setter 메서드를 추가할 수 있습니다.
        public void setCampRating(CampRatingDTO campRating) {
            this.campRating = campRating;
        }

        public void setReviewList(List<CampReviewListDTO> reviewList) {
            this.reviewList = reviewList;
        }
    }

    // 리뷰 작성 엔드포인트 ㅈㅇㅈ
    @PostMapping("/{campId}/reviews")
    public ResponseEntity<?> createCampReview(@PathVariable Integer campId,
            @Valid @RequestBody CampReviewDTO campReviewDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        // 인증된 사용자의 정보를 SecurityContextHolder에서 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.findByUsername(currentPrincipalName)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        campReviewDTO.setUserId(user.getId());

        // 리뷰 작성 로직
        // 사용자 ID와 캠핑장 ID를 DTO에 설정
        campReviewDTO.setUserId(user.getId());
        campReviewDTO.setCampId(campId);

        // 리뷰 저장 로직을 CampService에 위임
        CampReview savedReview = campService.saveCampReview(campReviewDTO);

        // 리뷰 저장에 성공했다면, 리뷰 정보와 함께 CREATED 응답 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }

    // ===================================================================

    // ME 관심캠핑장 목록 페이지 요청
    // localhost:8080/camp/bookmark-list
    @GetMapping("/bookmark-list")
    public ResponseEntity<?> campBookmarkPage() {
        // @RequestHeader("Authorization") String token
        // DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        // Integer userId = decodedJWT.getClaim("id")
        CampRespDTO.CampBookMarkListDTO responseDTO = campService.campBookMarkPage(1);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // 내 캠핑장 연도별 목록 조회
    @GetMapping("/myCamp")
    public ResponseEntity<?> myCampList(@ModelAttribute CampReqDTO.MyCampListDTO requestDTO 
    						/*,@RequestHeader("Authorization") String token*/){
    	//DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
    	//Integer userId = decodedJWT.getClaim("id").asInt();
    	// 테스트 용 하드 코딩
    	CampRespDTO.MyCampListDTO responseDTO = campService.myCampFieldList(1 , requestDTO);
    	//OrderRespDTO.myCampFieldListDTO responseDTO = orderService.myCampFieldList(userId, requestDTO);
    	return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }
    
}
