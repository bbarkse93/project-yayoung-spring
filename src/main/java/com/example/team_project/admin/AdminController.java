package com.example.team_project.admin;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@RequiredArgsConstructor
@Controller
public class AdminController {

    private AdminService adminService;
    private HttpSession session;

    @GetMapping("/main")
    public String adminMainPage(){
        return "admin/playday";
    }

}
