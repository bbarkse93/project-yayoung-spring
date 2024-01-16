package com.example.team_project.admin;

import com.example.team_project._core.utils.ApiUtils;
import com.example.team_project.admin._dto.AdminReqDTO;
import com.example.team_project.admin._dto.AdminRespDTO;
import com.example.team_project.board.Board;
import com.example.team_project.board.BoardService;
import com.example.team_project.camp.CampService;
import com.example.team_project.notice.NoticeService;
import com.example.team_project.user.UserService;
import com.example.team_project.user._dto.UserReqDTO;
import com.example.team_project.user._dto.UserRespDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RequiredArgsConstructor
@RestController
public class AdminRestController {

    private final AdminService adminService;
    private final HttpSession session;

    // 캠핑장 삭제(PUT)
    @PutMapping("/camp/delete/{campId}")
    public ResponseEntity<?> deleteCamp(@PathVariable Integer campId) {
        String result = adminService.deleteCamp(campId);
        return ResponseEntity.ok().body(ApiUtils.success(result));
    }

    // 캠핑장 리뷰 목록(GET) - 모달
    @GetMapping("/camp/review/{campId}")
    public ResponseEntity<?> campReviewList(@PathVariable Integer campId) {
        AdminRespDTO.CampReviewDTO campReviewDTO = adminService.campReviewList(campId);
        return ResponseEntity.ok().body(ApiUtils.success(campReviewDTO));
    }

    // 캠핑장 리뷰 삭제(DELETE)
    @DeleteMapping("/camp/review/delete/{reviewId}")
    public ResponseEntity<?> deleteCampReview(@PathVariable Integer reviewId) {
        String result = adminService.deleteReview(reviewId);
        return ResponseEntity.ok().body(ApiUtils.success(result));
    }

    // 유저 삭제(PUT)
    @PutMapping("/user/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        String result = adminService.deleteUser(userId);
        return ResponseEntity.ok().body(ApiUtils.success(result));
    }

    // 유저 상세 정보(GET) - 모달
    @GetMapping("/user/detail/{userId}")
    public ResponseEntity<?> detailUser(@PathVariable Integer userId) {
        AdminRespDTO.UserDetailDTO userDetailDTO = adminService.detailUser(userId);
        return ResponseEntity.ok().body(ApiUtils.success(userDetailDTO));
    }

    // faq 상세 내용(GET) - 모달
    @GetMapping("/faq/detail/{faqId}")
    public ResponseEntity<?> detailFaq(@PathVariable Integer faqId) {
        AdminRespDTO.FaqDetailDTO faqDetailDTO = adminService.detailFaq(faqId);
        System.out.println("faq 디테일 : " + faqDetailDTO.toString());
        return ResponseEntity.ok().body(ApiUtils.success(faqDetailDTO));
    }

    // faq 삭제(DELETE)
    @DeleteMapping("/faq/delete/{faqId}")
    public ResponseEntity<?> deleteFaq(@PathVariable Integer faqId) {
        String result = adminService.deleteFaq(faqId);
        return ResponseEntity.ok().body(ApiUtils.success(result));
    }

    //TODO : sessionUser 보내기
    // faq 등록(POST)
    @PostMapping("/faq/save")
    public ResponseEntity<?> saveFaq(@RequestBody AdminReqDTO.SaveFaqDTO requestDTO) {
        String result = adminService.saveFaq(requestDTO);
        return ResponseEntity.ok().body(ApiUtils.success(result));
    }

    //TODO : sessionUser 보내기
    // faq 수정(PUT)
    @PutMapping("/faq/update/{faqId}")
    public ResponseEntity<?> updateFaq(@RequestBody AdminReqDTO.UpdateFaqDTO requestDTO, @PathVariable Integer faqId) {
        String result = adminService.updateFaq(requestDTO, faqId);
        return ResponseEntity.ok().body(ApiUtils.success(result));
    }

    // notice 상세 내용(GET) - 모달
    @GetMapping("/notice/detail/{noticeId}")
    public ResponseEntity<?> detailNotice(@PathVariable Integer noticeId) {
        AdminRespDTO.NoticeDetailDTO noticeDetailDTO = adminService.detailNotice(noticeId);
        return ResponseEntity.ok().body(ApiUtils.success(noticeDetailDTO));
    }

    // notice 삭제(DELETE)
    @DeleteMapping("/notice/delete/{noticeId}")
    public ResponseEntity<?> deleteNotice(@PathVariable Integer noticeId) {
        String result = adminService.deleteNotice(noticeId);
        return ResponseEntity.ok().body(ApiUtils.success(result));
    }

    //TODO : sessionUser 보내기
    // notice 등록(POST)
    @PostMapping("/notice/save")
    public ResponseEntity<?> saveNotice(@RequestBody AdminReqDTO.SaveNoticeDTO requestDTO) {
        String result = adminService.saveNotice(requestDTO);
        return ResponseEntity.ok().body(ApiUtils.success(result));
    }

    //TODO : sessionUser 보내기
    // faq 수정(PUT)
    @PutMapping("/notice/update/{noticeId}")
    public ResponseEntity<?> updateNotice(@RequestBody AdminReqDTO.UpdateNoticeDTO requestDTO, @PathVariable Integer noticeId) {
        String result = adminService.updateNotice(requestDTO, noticeId);
        return ResponseEntity.ok().body(ApiUtils.success(result));
    }

}