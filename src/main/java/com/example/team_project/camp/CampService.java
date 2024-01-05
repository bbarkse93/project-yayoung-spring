package com.example.team_project.camp;

import com.example.team_project.camp.camp_bookmark.CampBookmarkJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CampService {

    private final CampBookmarkJPARepository campJPARepository;

}
