package com.example.team_project.camp;

import com.example.team_project._core.utils.ApiUtils;
import com.example.team_project.camp._dto.CampRespDTO;
import com.example.team_project.notice._dto.NoticeRespDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
