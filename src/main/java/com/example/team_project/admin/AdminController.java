package com.example.team_project.admin;

import com.example.team_project._core.errors.exception.Exception401;
import com.example.team_project.admin._dto.AdminReqDTO;
import com.example.team_project.admin._dto.AdminRespDTO;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.CampService;
import com.example.team_project.user.User;
import com.example.team_project.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final HttpSession session;




    // 로그인(GET)
    @GetMapping("/login")
    public String login (){
        return "admin/user_login";
    }


    // 로그인(POST)
    @PostMapping("/login")
    public String login(AdminReqDTO.LoginDTO dto) {
        System.out.println("로그인 값 잘 들어옴?" + dto.getUsername());
        System.out.println("로그인 값 잘 들어옴?" + dto.getPassword());
        User user = adminService.login(dto);

        // 로그인 처리
        session.setAttribute("sessionUser", user);

        return "redirect:/admin/camp/setting";
    }

    // 로그아웃(GET)
    @GetMapping("/logout")
    public String logout() {
        User user = (User) session.getAttribute("sessionUser");

        // 권한 검사
        if (user != null) {
            session.invalidate();
        } else {
            throw new Exception401("로그인 해주세요");
        }
        System.out.println("logout 실행됨");
        return "/admin/user_login";
    }

    /******************************************************************************************/

    // 캠핑장 페이지 요청(GET) + 검색
    @GetMapping("/camp/setting")
    public String campSettingSearch(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "") String keyword, Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 페이지당 게시물 수 상수로 고정
        final int PAGESIZE = 10;

        // 전체목록
        int campAllSize = adminService.campList(keyword).size();

        // 페이징목록
        List<AdminRespDTO.CampDTO> campDTOList = adminService.campSearch(page, keyword, PAGESIZE);

        model.addAttribute("campDTOList", campDTOList);
        model.addAttribute("sessionUser", sessionUser);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("keyword", keyword);
        model.addAttribute("first", page == 0);
        model.addAttribute("last",
                (campAllSize / PAGESIZE) == page
                        || ((campAllSize % PAGESIZE == 0) && (campAllSize / PAGESIZE) - 1 == page));
        return "admin/camp_setting";
    }


    // 캠핑장 등록 요청(POST)
    @PostMapping("/camp/save")
    public String saveCamp(@RequestBody AdminReqDTO.SaveCampDTO requestDTO){
        String result = adminService.saveCamp(requestDTO);
        return "redirect:/admin/camp/setting";
    }


    /******************************************************************************************/

    // 캠핑장 현황 페이지 요청(GET)
    @GetMapping("/camp/current")
    public String campCurrentSearch(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "") String keyword, Model model) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        // 페이지당 게시물 수 상수로 고정
        final int PAGESIZE = 10;

        // 전체목록
        int campAllSize = adminService.ratingCampList(keyword).size();

        // 페이징목록
        List<AdminRespDTO.RatingCampDTO> ratingCampDTOList = adminService.ratingCampSearch(page, keyword, PAGESIZE);

        model.addAttribute("ratingCampDTOList", ratingCampDTOList);
        model.addAttribute("sessionUser", sessionUser);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("keyword", keyword);
        model.addAttribute("first", page == 0);
        model.addAttribute("last",
                (campAllSize / PAGESIZE) == page
                        || ((campAllSize % PAGESIZE == 0) && (campAllSize / PAGESIZE) - 1 == page));
        return "admin/camp_current";
    }


    /******************************************************************************************/

    // 회원 관리 페이지 요청(GET)
    @GetMapping("/user")
    public String userSearch(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "") String keyword, Model model) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        // 페이지당 게시물 수 상수로 고정
        final int PAGESIZE = 10;

        // 전체목록
        int userAllSize = adminService.userList(keyword).size();

        // 페이징목록
        List<AdminRespDTO.UserDTO> userDTOList = adminService.userSearch(page, keyword, PAGESIZE);

        model.addAttribute("userDTOList", userDTOList);
        model.addAttribute("sessionUser", sessionUser);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("keyword", keyword);
        model.addAttribute("first", page == 0);
        model.addAttribute("last",
                (userAllSize / PAGESIZE) == page
                        || ((userAllSize % PAGESIZE == 0) && (userAllSize / PAGESIZE) - 1 == page));
        return "admin/user_management";
    }

    /******************************************************************************************/

    // FAQ 관리 페이지 요청(GET)
    @GetMapping("/customer/faq")
    public String faqSearch(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "1") Integer categoryId, Model model) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        // 페이지당 게시물 수 상수로 고정
        final int PAGESIZE = 5;

        // 전체목록
        // 결제
        int paymentAllSize = adminService.faqList(keyword).getPaymentDTOList().size();
        // 결제
        int userAllSize = adminService.faqList(keyword).getUserDTOList().size();

        // 페이징목록
        // 결제
        List<AdminRespDTO.FaqDTOList.PaymentDTO> paymentDTOList = adminService.faqSearch(page, keyword, PAGESIZE, categoryId).getPaymentDTOList();
        List<AdminRespDTO.FaqDTOList.UserDTO> userDTOList = adminService.faqSearch(page, keyword, PAGESIZE, categoryId).getUserDTOList();

        model.addAttribute("paymentDTOList", paymentDTOList);
        model.addAttribute("sessionUser", sessionUser);
        model.addAttribute("userDTOList", userDTOList);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("keyword", keyword);
        model.addAttribute("first", page == 0);
        model.addAttribute("payLast",
                (paymentAllSize / PAGESIZE) == page
                        || ((paymentAllSize % PAGESIZE == 0) && (paymentAllSize / PAGESIZE) - 1 == page));
        model.addAttribute("userLast",
                (userAllSize / PAGESIZE) == page
                        || ((userAllSize % PAGESIZE == 0) && (userAllSize / PAGESIZE) - 1 == page));
        return "admin/customer_faq";
    }

    /******************************************************************************************/

    // 공지사항 관리 페이지 요청(GET)
    @GetMapping("/customer/notice")
    public String noticeSearch(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "") String keyword, Model model) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        // 페이지당 게시물 수 상수로 고정
        final int PAGESIZE = 5;

        // 전체목록
        int noticeAllSize = adminService.noticeList(keyword).size();

        // 페이징목록
        // 결제
        List<AdminRespDTO.NoticeDTO> noticeDTOList = adminService.noticeSearch(page, keyword, PAGESIZE);

        model.addAttribute("noticeDTOList", noticeDTOList);
        model.addAttribute("sessionUser", sessionUser);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("keyword", keyword);
        model.addAttribute("first", page == 0);
        model.addAttribute("last",
                (noticeAllSize / PAGESIZE) == page
                        || ((noticeAllSize % PAGESIZE == 0) && (noticeAllSize / PAGESIZE) - 1 == page));
        return "admin/customer_notice";
    }

    /******************************************************************************************/

}
