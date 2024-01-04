package com.example.team_project.camp_field;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/camp-field")
@RequiredArgsConstructor
@RestController
public class CampFieldRestController {

    private final CampFieldService campFieldService;

}
