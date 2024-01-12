package com.example.team_project.admin;

import com.example.team_project.admin._dto.AdminRespDTO;
import com.example.team_project.camp.CampService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final HttpSession session;

    // TODO 로그인, 로그아웃, (비밀번호 변경 로직) 만들어야 함....

    // 캠핑장 관리 페이지 요청(GET)
    @GetMapping("/camp/setting")
    public String campSettingPage(Model model){
        AdminRespDTO.CampListDTO campListDTO = adminService.campList();
        model.addAttribute("campListDTO", campListDTO);
        return "admin/camp_setting";
    }


    // 캠핑장 현황 페이지 요청(GET)
    @GetMapping("/camp/current")
    public String currentCampPage(){
        return "admin/camp_current";
    }

    // 회원 관리 페이지 요청(GET)
    @GetMapping("/user")
    public String userPage(){
        return "admin/user_management";
    }

    // FAQ 관리 페이지 요청(GET)
    @GetMapping("/customer/faq")
    public String faqPage(){
        return "admin/customer_faq";
    }

    // 공지사항 관리 페이지 요청(GET)
    @GetMapping("/customer/notice")
    public String noticePage(){
        return "admin/customer_notice";
    }
}
