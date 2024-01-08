package com.example.team_project.camp;

import com.example.team_project._core.utils.ApiUtils;
import com.example.team_project.camp._dto.CampReqDTO;
import com.example.team_project.camp._dto.CampRespDTO;
import com.example.team_project.notice._dto.NoticeRespDTO;
import com.example.team_project.order._dto.OrderRespDTO;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.team_project.camp._dto.CampRespDTO.CampListDTO;;

@RequestMapping("/camp")
@RequiredArgsConstructor
@RestController
public class CampRestController {

    private final CampService campService;

    // 캠핑장 리스트 페이지
    // @GetMapping("/list")
    // public ResponseEntity<?> getCampList() {
    //     List<Camp> camps = campService.getAllCamps();
    //     return ResponseEntity.ok(camps);
    // }

    @GetMapping("/list")
    public ResponseEntity<List<CampListDTO>> getAllCamps() {
        List<CampListDTO> campDTOs = campService.getAllCamps();
        return ResponseEntity.ok(campDTOs);
    }


    // 캠핑장 상세정보 페이지
    // @GetMapping("/{id}")
    // public ResponseEntity<?> getCampDetail(@PathVariable Integer id) {
    //     Camp camp = campService.getCampById(id);
    //     if(camp == null) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     return ResponseEntity.ok(camp);
    // }

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
    public ResponseEntity<?> myCampList(/*@RequestParam("year") CampReqDTO.MyCampListDTO requestDTO ,@RequestHeader("Authorization") String token*/){
    	//DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
    	//Integer userId = decodedJWT.getClaim("id").asInt();
    	// 테스트 용 하드 코딩
    	CampReqDTO.MyCampListDTO requestDTO = new CampReqDTO.MyCampListDTO();
    	requestDTO.setYear(2024);
    	CampRespDTO.MyCampListDTO responseDTO = campService.myCampFieldList(1 , requestDTO);
    	//OrderRespDTO.myCampFieldListDTO responseDTO = orderService.myCampFieldList(userId, requestDTO);
    	return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }
    
}
