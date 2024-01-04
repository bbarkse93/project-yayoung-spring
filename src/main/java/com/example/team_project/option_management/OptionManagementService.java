package com.example.team_project.option_management;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class OptionManagementService {

    private final OptionManagementJPARepository optionManagementJPARepository;
}
