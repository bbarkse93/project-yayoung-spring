package com.example.team_project.admin;

import com.example.team_project.admin._dto.AdminRespDTO;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.CampService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final HttpSession session;

    // TODO 로그인, 로그아웃, (비밀번호 변경 로직) 만들어야 함....

    // 캠핑장 페이지 요청(GET) + 검색
    @GetMapping("/camp/setting")
    public String campSettingSearch(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "") String keyword, Model model) {
        // 페이지당 게시물 수 상수로 고정
        final int PAGESIZE = 10;

        // 전체목록
        int campAllSize = adminService.campList(keyword).size();

        // 페이징목록
        List<AdminRespDTO.CampDTO> campDTOList = adminService.campSearch(page, keyword, PAGESIZE);

        model.addAttribute("campDTOList", campDTOList);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("keyword", keyword);
        model.addAttribute("first", page == 0);
        model.addAttribute("last",
                (campAllSize / PAGESIZE) == page
                        || ((campAllSize % PAGESIZE == 0) && (campAllSize / PAGESIZE) - 1 == page));
        return "admin/camp_setting";
    }


    /******************************************************************************************/

    // 캠핑장 현황 페이지 요청(GET)
    @GetMapping("/camp/current")
    public String campCurrentSearch(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "") String keyword, Model model) {
        // 페이지당 게시물 수 상수로 고정
        final int PAGESIZE = 10;

        // 전체목록
        int campAllSize = adminService.ratingCampList(keyword).size();

        // 페이징목록
        List<AdminRespDTO.RatingCampDTO> ratingCampDTOList = adminService.ratingCampSearch(page, keyword, PAGESIZE);

        model.addAttribute("ratingCampDTOList", ratingCampDTOList);
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
        // 페이지당 게시물 수 상수로 고정
        final int PAGESIZE = 10;

        // 전체목록
        int userAllSize = adminService.userList(keyword).size();

        // 페이징목록
        List<AdminRespDTO.UserDTO> userDTOList = adminService.userSearch(page, keyword, PAGESIZE);

        model.addAttribute("userDTOList", userDTOList);
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
        // 페이지당 게시물 수 상수로 고정
        final int PAGESIZE = 5;

        // 전체목록
        int noticeAllSize = adminService.noticeList(keyword).size();

        // 페이징목록
        // 결제
        List<AdminRespDTO.NoticeDTO> noticeDTOList = adminService.noticeSearch(page, keyword, PAGESIZE);

        model.addAttribute("noticeDTOList", noticeDTOList);
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

    // 로그인(GET)
    @GetMapping("/login")
    public String login (){
        return "admin/user_login";
    }

}
