package com.example.team_project.option;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/option")
@RequiredArgsConstructor
@RestController
public class OptionRestController {

    private final OptionService OptionService;
}
