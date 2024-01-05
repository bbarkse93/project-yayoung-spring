package com.example.team_project.board;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/board")
@RequiredArgsConstructor
@RestController
public class BoardRestController {

    private final BoardService boardService;

}
