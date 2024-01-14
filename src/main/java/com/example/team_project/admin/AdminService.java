package com.example.team_project.admin;

import com.example.team_project._core.errors.exception.Exception404;
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

    // 캠핑장 목록(캠핑장 수)
    public List<AdminRespDTO.CampDTO> campList(String keyword) {
        System.out.println("키워드 : " + keyword);
        List<Camp> campList = campJPARepository.mfindSearchAll(keyword);
        System.out.println("페이지나와라 : " + campList.size());
        return campList.stream().map(AdminRespDTO.CampDTO::new).collect(Collectors.toList());
    }

    // 캠핑장 목록 페이징(페이징 된 화면 수)
    public List<AdminRespDTO.CampDTO> campSearchPage(Integer page, String keyword, Integer pageSize) {
        System.out.println("키워드 : " + keyword);
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
        Page<Camp> campPG = campJPARepository.mfindSearchPageAll(keyword, pageable);
        System.out.println("페이지 : " + campPG.toString());
        return campPG.stream().map(AdminRespDTO.CampDTO::new).collect(Collectors.toList());
    }

    // 캠핑장 삭제
    public String deleteCamp(Integer campId) {
        Camp camp = campJPARepository.findById(campId).orElseThrow(() -> new Exception404("해당 캠핑장을 찾을 수 없습니다." + campId));
        camp.updateIsDelete(true);
        return "삭제에 성공했습니다.";
    }

}