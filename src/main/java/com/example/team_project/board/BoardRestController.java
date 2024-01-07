package com.example.team_project.board;

import com.example.team_project._core.utils.ApiUtils;
import com.example.team_project.board._dto.BoardRespDTO;
import com.example.team_project.notice._dto.NoticeRespDTO;
import com.example.team_project.user._dto.UserRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/board")
@RequiredArgsConstructor
@RestController
public class BoardRestController {

    private final BoardService boardService;

    // ME FAQ 페이지 요청
    // localhost:8080/board/list
    @GetMapping("/list")
    public ResponseEntity<?> boardPage() {
        BoardRespDTO.BoardListDTO responseDTO = boardService.boardPage();
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

}
