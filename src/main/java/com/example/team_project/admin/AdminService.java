package com.example.team_project.admin;

import com.example.team_project.admin._dto.AdminRespDTO;
import com.example.team_project.board.BoardJPARepository;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.CampJPARepository;
import com.example.team_project.notice.NoticeJPARepository;
import com.example.team_project.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<AdminRespDTO.CampDTO> campList() {
        List<Camp> campList = campJPARepository.findAll();
        return campList.stream().map(AdminRespDTO.CampDTO::new).collect(Collectors.toList());
    }

    // 캠핑장 목록 페이징 요청
    public List<AdminRespDTO.CampDTO> campPage(Integer page, Integer pageSize) {
          Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
        // page는 상수가 아니기 때문에 받아야 한다.
        Page<Camp> campPG = campJPARepository.findAll(pageable);
        return campPG.stream().map(AdminRespDTO.CampDTO::new).collect(Collectors.toList());

    }
}