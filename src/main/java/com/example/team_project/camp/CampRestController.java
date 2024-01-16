package com.example.team_project.camp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.team_project._core.utils.ApiUtils;
import com.example.team_project._core.utils.JwtTokenUtils;
import com.example.team_project.camp._dto.CampReqDTO;
import com.example.team_project.camp._dto.CampRespDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampDetailDTO;
import com.example.team_project.camp.camp_bookmark.CampBookmark;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;;

@RequestMapping("/camp")
@RequiredArgsConstructor
@RestController
public class CampRestController {

    private final CampService campService;

    //캠핑장 목록 조회(필터 적용 가능)
    @GetMapping("/list")
    public ResponseEntity<?> getAllCamps(@ModelAttribute CampReqDTO.CampListDTO requestDTO) {
        // 인증검사

        // 핵심로직
        CampRespDTO.CampListDTO responseDTO = campService.getAllCamps(requestDTO);
        return ResponseEntity.ok(ApiUtils.success(responseDTO));
    }


    // 캠핑장 상세정보 페이지
     @GetMapping("/{campId}")
     public ResponseEntity<?> getCampDetail(@PathVariable Integer campId) {
         CampDetailDTO camp = campService.getCampDetail(campId);
         return ResponseEntity.ok(ApiUtils.success(camp));
     }

    // 관심 캠핑장 등록 기능
    @PostMapping("/bookmark")
    public ResponseEntity<?> addBookmark(
            @Valid CampReqDTO.CampBookmarkDTO requestDTO
     , @RequestHeader("Authorization") String token
    ) {
        // 토큰 처리를 위한 서비스를 통해 userId 추출
         DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
         Integer userId = decodedJWT.getClaim("id").asInt();
        System.out.println("컨트롤러");
//        campService.addBookmark(1, requestDTO);
        campService.addBookmark(userId, requestDTO);

//         OrderRespDTO.myCampFieldListDTO responseDTO =
//         orderService.myCampFieldList(userId, requestDTO);
        return ResponseEntity.ok(ApiUtils.success("북마크 성공"));
    }

    // 관심 캠핑장 등록 해제 기능
    @DeleteMapping("/bookmark")
    public ResponseEntity<?> removeBookmark(@Valid CampReqDTO.CampBookmarkDeleteDTO requestDTO
           , @RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();

//        campService.removeBookmark(1, requestDTO);
        campService.removeBookmark(userId, requestDTO);
        return ResponseEntity.ok().body(ApiUtils.success("북마크 해제"));
    }

    // 관심 캠핑장 목록 조회 엔드포인트
    @GetMapping("/bookmarks")
    public ResponseEntity<?> bookmarkList (@RequestHeader("Authorization") String token) {
      DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
      Integer userId = decodedJWT.getClaim("id").asInt();    	
    	
    	//테스트 용 하드 코딩
//        List<CampBookmark> responseDTO = campService.bookmarkList(1);
        List<CampBookmark> responseDTO = campService.bookmarkList(userId);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // 캠핑장 상세 정보 이미지 데이터 제공
//    @GetMapping("/{campId}/details")
//    public ResponseEntity<?> getCampDetails(@PathVariable Integer campId) {
//        CampDetailDTO campDetails = campService.getCampDetails(campId);
//        return ResponseEntity.ok(campDetails);
//    }

    // ME 관심캠핑장 목록 페이지 요청
    // localhost:8080/camp/bookmark-list
    @GetMapping("/bookmark-list")
    public ResponseEntity<?> campBookmarkPage(@RequestHeader("Authorization") String token) {
         DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
         Integer userId = decodedJWT.getClaim("id").asInt();
        CampRespDTO.CampBookMarkListDTO responseDTO = campService.campBookMarkPage(userId);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // 내 캠핑장 연도별 목록 조회
    @GetMapping("/myCamp")
    public ResponseEntity<?> myCampList(@ModelAttribute CampReqDTO.MyCampListDTO requestDTO 
    						,@RequestHeader("Authorization") String token){
    	DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
    	Integer userId = decodedJWT.getClaim("id").asInt();
    	// 테스트 용 하드 코딩
//    	CampRespDTO.MyCampListDTO responseDTO = campService.myCampFieldList(1 , requestDTO);
    	CampRespDTO.MyCampListDTO responseDTO = campService.myCampFieldList(userId, requestDTO);
    	return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // 전체 캠핑장 검색
    @GetMapping("/search")
    public ResponseEntity<?> searchCamp(@RequestParam(defaultValue = "") String keyword){
        System.out.println("ControllerKeyword는? " + keyword);
        CampRespDTO.SearchCampDTO responseDTO = campService.searchCamp(keyword);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

}
