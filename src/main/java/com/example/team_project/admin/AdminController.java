package com.example.team_project.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/current-camp")
    public String currentCampPage(){
        return "admin/camp_current";
    }

    @GetMapping("/user")
    public String userPage(){
        return "admin/user_management";
    }

    @GetMapping("/customer/faq")
    public String faqPage(){
        return "admin/customer_faq";
    }

    @GetMapping("/customer/notice")
    public String noticePage(){
        return "admin/customer_notice";
    }
}
