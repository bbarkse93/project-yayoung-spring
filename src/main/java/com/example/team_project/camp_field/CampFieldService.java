package com.example.team_project.camp_field;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CampFieldService {

    private final CampFieldJPARepository campFieldJPARepository;

}
