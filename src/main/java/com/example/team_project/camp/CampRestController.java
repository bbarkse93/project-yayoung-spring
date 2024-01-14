package com.example.team_project.camp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.example.team_project.camp._dto.CampRespDTO.CampListDTO;
import com.example.team_project.camp.camp_bookmark.CampBookmark;
import lombok.RequiredArgsConstructor;;

@RequestMapping("/camp")
@RequiredArgsConstructor
@RestController
public class CampRestController {

    private final CampService campService;

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
    //캠핑장 목록 조회(필터 적용 가능)(요청 DTO를 어떤 형식으로 받을지 미정)
    @GetMapping("/list")
    public ResponseEntity<?> getAllCamps() {
        // 인증검사
    	//테스트용 하드 코딩
    	CampReqDTO.CampListDTO requestDTO = new CampReqDTO.CampListDTO();
    	List<String> optionNames = new ArrayList<>();
//    	optionNames.add("카라반");
//    	optionNames.add("산");
    	List<String> reigonNames = new ArrayList<>();
//    	reigonNames.add("충남");
    	requestDTO.setOptionNames(optionNames);
    	requestDTO.setRegionNames(reigonNames);
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

    // 관심 캠핑장 등록 기능
    @PostMapping("/bookmark")
    public ResponseEntity<?> addBookmark(
            @RequestBody CampReqDTO.CampBookmarkDTO dto
    // , @RequestHeader("Authorization") String token
    ) {
        // 토큰 처리를 위한 서비스를 통해 userId 추출
        // DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        // Integer userId = decodedJWT.getClaim("id").asInt();
        System.out.println("컨트롤러");
        campService.addBookmark(1, dto);

        // OrderRespDTO.myCampFieldListDTO responseDTO =
        // orderService.myCampFieldList(userId, requestDTO);
        return ResponseEntity.ok(ApiUtils.success("북마크 성공"));
    }

    // 관심 캠핑장 등록 해제 기능
    @DeleteMapping("/bookmark")
    public ResponseEntity<?> removeBookmark(@RequestParam Integer campId,
            @RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();

        campService.removeBookmark(userId, campId);
        return ResponseEntity.ok().build();
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
