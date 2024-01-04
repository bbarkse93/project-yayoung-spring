package com.example.team_project.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/notice")
@RequiredArgsConstructor
@RestController
public class NoticeRestController {

    private final NoticeService noticeService;

}
