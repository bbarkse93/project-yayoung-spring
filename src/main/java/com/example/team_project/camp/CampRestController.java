package com.example.team_project.camp;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.team_project._core.utils.ApiUtils;
import com.example.team_project.camp._dto.CampReqDTO;
import com.example.team_project.camp._dto.CampRespDTO;
import com.example.team_project.camp._dto.CampRespDTO.CampListDTO;
import com.example.team_project.camp.camp_bookmark.CampBookmark;
import lombok.RequiredArgsConstructor;;

@RequestMapping("/camp")
@RequiredArgsConstructor
@RestController
public class CampRestController {

    private final CampService campService;

    // 캠핑장 리스트 페이지
    // @GetMapping("/list")
    // public ResponseEntity<?> getCampList() {
    // List<Camp> camps = campService.getAllCamps();
    // return ResponseEntity.ok(camps);
    // }

    @GetMapping("/list")
    public ResponseEntity<?> getAllCamps() {
        List<CampListDTO> campDTOs = campService.getAllCamps();
        return ResponseEntity.ok(ApiUtils.success(campDTOs));
    }

    // 캠핑장 상세정보 페이지
    // @GetMapping("/{id}")
    // public ResponseEntity<?> getCampDetail(@PathVariable Integer id) {
    // Camp camp = campService.getCampById(id);
    // if(camp == null) {
    // return ResponseEntity.notFound().build();
    // }
    // return ResponseEntity.ok(camp);
    // }

    // 관심 캠핑장 등록 기능
    @PostMapping("/bookmark")
    public ResponseEntity<?> addBookmark(@RequestParam Integer userId, @RequestParam Integer campId) {
        CampBookmark campBookmark = campService.addBookmark(userId, campId);
        return ResponseEntity.ok(campBookmark);
    }

    // 관심 캠핑장 등록 해제 기능
    @PostMapping("/bookmark-remove")
    public ResponseEntity<?> bookmarkRemove(@RequestParam Integer userId, @RequestParam Integer campId) {
        campService.bookmarkRemove(userId, campId);
        return ResponseEntity.ok().build();
    }

    // 사용자의 관심 캠핑장 목록 조회 엔드포인트
    @GetMapping("/bookmarks/{userId}")
    public ResponseEntity<?> getUserBookmarks(@PathVariable Integer userId) {
        List<CampBookmark> bookmarks = campService.getUserBookmarks(userId);
        return ResponseEntity.ok(bookmarks);
    }

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
    
    //내 캠핑장 연도별 목록 조회
    @GetMapping("/myCamp")
    public ResponseEntity<?> myCampList(@ModelAttribute CampReqDTO.MyCampListDTO requestDTO /*,@RequestHeader("Authorization") String token*/){
    	//DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
    	//Integer userId = decodedJWT.getClaim("id").asInt();
    	// 테스트 용 하드 코딩
    	CampRespDTO.MyCampListDTO responseDTO = campService.myCampFieldList(1 , requestDTO);
    	//OrderRespDTO.myCampFieldListDTO responseDTO = orderService.myCampFieldList(userId, requestDTO);
    	return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }
    
    //캠프장 아이디를 받아 캠프 구역 목록 조회
    @GetMapping("/field-list")
    public ResponseEntity<?> campFieldList(@ModelAttribute CampReqDTO.CampFieldListDTO requestDTO){
    	CampRespDTO.CampFieldListDTO responseDTO = campService.campFieldList(requestDTO);
    	return ResponseEntity.ok(ApiUtils.success(responseDTO));
    }
    
    
}
