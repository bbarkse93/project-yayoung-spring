package com.example.team_project.notice;

import com.example.team_project.notice._dto.NoticeRespDTO;
import com.example.team_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeJPARepository noticeJPARepository;

    public NoticeRespDTO.NoticeListDTO noticePage() {
        List<Notice> noticeList = noticeJPARepository.findAll();

        return new NoticeRespDTO.NoticeListDTO(noticeList);
    }
}
