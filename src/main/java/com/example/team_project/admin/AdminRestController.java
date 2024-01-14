package com.example.team_project.admin;

import com.example.team_project._core.utils.ApiUtils;
import com.example.team_project.admin._dto.AdminRespDTO;
import com.example.team_project.board.BoardService;
import com.example.team_project.camp.CampService;
import com.example.team_project.notice.NoticeService;
import com.example.team_project.user.UserService;
import com.example.team_project.user._dto.UserReqDTO;
import com.example.team_project.user._dto.UserRespDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RequiredArgsConstructor
@RestController
public class AdminRestController {

    private final AdminService adminService;

    // 캠핑장 삭제(PUT)
    @PutMapping("/camp/delete/{campId}")
    public ResponseEntity<?> campDelete(@PathVariable Integer campId){
        String result = adminService.deleteCamp(campId);
        return ResponseEntity.ok().body(ApiUtils.success(result));
    }

    // 캠핑장 리뷰 목록(GET) - 모달
    @GetMapping("/camp/review/{campId}")
    public ResponseEntity<?> campReview(@PathVariable Integer campId){
        AdminRespDTO.CampReviewDTO campReviewDTO = adminService.campReviewList(campId);
        return ResponseEntity.ok().body(ApiUtils.success(campReviewDTO));
    }

    // 캠핑장 리뷰 삭제(DELETE)
    @DeleteMapping("/camp/review/delete/{reviewId}")
    public ResponseEntity<?> campReviewDelete(@PathVariable Integer reviewId){
        String result = adminService.deleteReview(reviewId);
        return ResponseEntity.ok().body(ApiUtils.success(result));
    }
}
