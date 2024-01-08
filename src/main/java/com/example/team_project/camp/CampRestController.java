package com.example.team_project.camp;

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
}
