package com.example.team_project.camp_field;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class CampFieldService {

    private final CampFieldJPARepository campFieldJPARepository;

    

}
