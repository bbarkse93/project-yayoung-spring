package com.example.team_project.admin;

import com.example.team_project.admin._dto.AdminRespDTO;
import com.example.team_project.board.BoardJPARepository;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.CampJPARepository;
import com.example.team_project.notice.NoticeJPARepository;
import com.example.team_project.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class AdminService {

    private final CampJPARepository campJPARepository;
    private final UserJPARepository userJPARepository;
    private final BoardJPARepository boardJPARepository;
    private final NoticeJPARepository noticeJPARepository;

    // 캠핑장 목록 요청
    public AdminRespDTO.CampListDTO campList() {
        List<Camp> campList = campJPARepository.findAll();
        return new AdminRespDTO.CampListDTO(campList);
    }
}
