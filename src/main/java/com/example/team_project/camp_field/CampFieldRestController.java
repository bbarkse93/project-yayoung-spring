package com.example.team_project.camp_field;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.team_project._core.utils.ApiUtils;
import com.example.team_project.camp_field._dto.CampFieldReqDTO;
import com.example.team_project.camp_field._dto.CampFieldRespDTO;

@RequestMapping("/camp-field")
@RequiredArgsConstructor
@RestController
public class CampFieldRestController {

    private final CampFieldService campFieldService;
    
   

}
