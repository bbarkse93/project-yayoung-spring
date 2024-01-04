package com.example.team_project.option_management;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/option-management")
@RequiredArgsConstructor
@RestController
public class OptionManagementRestController {

    private final OptionManagementService optionManagementService;
}
