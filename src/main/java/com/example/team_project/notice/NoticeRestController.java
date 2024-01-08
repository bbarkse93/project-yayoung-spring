package com.example.team_project.notice;

import com.example.team_project._core.utils.ApiUtils;
import com.example.team_project.notice._dto.NoticeRespDTO;
import com.example.team_project.user._dto.UserRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/notice")
@RequiredArgsConstructor
@RestController
public class NoticeRestController {

    private final NoticeService noticeService;

    // ME 공지사항 페이지 요청
    // localhost:8080/notice/list
    @GetMapping("/list")
    public ResponseEntity<?> noticePage() {
        NoticeRespDTO.NoticeListDTO responseDTO = noticeService.noticePage();
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }


}
