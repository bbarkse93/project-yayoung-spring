package com.example.team_project.admin;

import com.example.team_project._core.errors.exception.Exception401;
import com.example.team_project._core.errors.exception.UnAuthorizedException;
import com.example.team_project.admin._dto.AdminReqDTO;
import com.example.team_project.admin._dto.AdminRespDTO;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.CampService;
import com.example.team_project.user.User;
import com.example.team_project.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final HttpSession session;

    // 로그인 페이지 요청(GET)
    @GetMapping("/login")
    public String login (){
        return "admin/user_login";
    }


    // 로그인(POST)
    @PostMapping("/login")
    public String login(AdminReqDTO.LoginDTO dto) {
        User user = adminService.login(dto);

        // 로그인 처리
        session.setAttribute("sessionUser", user);

        return "redirect:/admin/camp/setting";
    }

    // 로그아웃(GET)
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "/admin/user_login";
    }

    /******************************************************************************************/

    // 캠핑장 등록 페이지(GET)
    @GetMapping("/camp/save")
    public String campSavePage(Model model){
        User sessionUser = (User) session.getAttribute("sessionUser");
        model.addAttribute("sessionUser", sessionUser);

        // 캠핑 모달 창 속 옵션 리스트
        List<AdminRespDTO.OptionDTO> optionDTOList = adminService.OptionList();
        List<AdminRespDTO.OptionDTO> environmentList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 1).toList();
        List<AdminRespDTO.OptionDTO> typeList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 2).toList();
        List<AdminRespDTO.OptionDTO> siteList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 3).toList();
        List<AdminRespDTO.OptionDTO> mainFacilityList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 4).toList();
        List<AdminRespDTO.OptionDTO> haveFacilityList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 5).toList();
        List<AdminRespDTO.OptionDTO> exerciseFacilityList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 6).toList();
        List<AdminRespDTO.OptionDTO> programList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 7).toList();
        List<AdminRespDTO.OptionDTO> rentalList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 8).toList();
        List<AdminRespDTO.OptionDTO> sellList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 9).toList();
        model.addAttribute("environmentList", environmentList);
        model.addAttribute("typeList", typeList);
        model.addAttribute("siteList", siteList);
        model.addAttribute("mainFacilityList", mainFacilityList);
        model.addAttribute("haveFacilityList", haveFacilityList);
        model.addAttribute("exerciseFacilityList", exerciseFacilityList);
        model.addAttribute("programList", programList);
        model.addAttribute("rentalList", rentalList);
        model.addAttribute("sellList", sellList);
        return "/admin/camp_save";
    }

    // 캠핑장 등록 요청(POST)
    @PostMapping("/camp/save")
    public String saveCamp(@ModelAttribute AdminReqDTO.SaveCampDTO requestDTO){
        try{
            Integer campId = adminService.saveCamp(requestDTO);
            adminService.saveCampImage(requestDTO, campId);
            adminService.saveCampField(requestDTO, campId);
            adminService.saveOptionManagement(requestDTO, campId);
        }catch (Exception e){
            e.getMessage();
        }
        return "redirect:/admin/camp/setting";
    }

    /******************************************************************************************/

    // 캠핑장 페이지 요청(GET) + 검색
    @GetMapping("/camp/setting")
    public String campSettingSearch(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "") String keyword, Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        model.addAttribute("sessionUser", sessionUser);

        // 페이지당 게시물 수 상수로 고정
        final int PAGESIZE = 5;

        // 전체목록
        int campAllSize = adminService.campList(keyword).size();

        // 페이징목록
        List<AdminRespDTO.CampDTO> campDTOList = adminService.campSearch(page, keyword, PAGESIZE);
        model.addAttribute("campDTOList", campDTOList);

        // 캠핑 모달 창 속 옵션 리스트
        List<AdminRespDTO.OptionDTO> optionDTOList = adminService.OptionList();
        List<AdminRespDTO.OptionDTO> environmentList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 1).toList();
        List<AdminRespDTO.OptionDTO> typeList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 2).toList();
        List<AdminRespDTO.OptionDTO> siteList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 3).toList();
        List<AdminRespDTO.OptionDTO> mainFacilityList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 4).toList();
        List<AdminRespDTO.OptionDTO> haveFacilityList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 5).toList();
        List<AdminRespDTO.OptionDTO> exerciseFacilityList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 6).toList();
        List<AdminRespDTO.OptionDTO> programList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 7).toList();
        List<AdminRespDTO.OptionDTO> rentalList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 8).toList();
        List<AdminRespDTO.OptionDTO> sellList = optionDTOList.stream().filter(optionDTO -> optionDTO.getOptionCategoryId() == 9).toList();
        model.addAttribute("environmentList", environmentList);
        model.addAttribute("typeList", typeList);
        model.addAttribute("siteList", siteList);
        model.addAttribute("mainFacilityList", mainFacilityList);
        model.addAttribute("haveFacilityList", haveFacilityList);
        model.addAttribute("exerciseFacilityList", exerciseFacilityList);
        model.addAttribute("programList", programList);
        model.addAttribute("rentalList", rentalList);
        model.addAttribute("sellList", sellList);

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


    // 캠핑장 수정 요청(POST)
    @PostMapping("/camp/update/{campId}")
    public String saveCamp(@ModelAttribute AdminReqDTO.UpdateCampDTO requestDTO, @PathVariable Integer campId){
        try{
            adminService.updateCamp(requestDTO ,campId);
            adminService.updateCampImage(requestDTO, campId);
            adminService.updateCampField(requestDTO, campId);
            adminService.updateOptionManagement(requestDTO, campId);
        }catch (Exception e){
            e.getMessage();
        }
        return "redirect:/admin/camp/setting";
    }


    /******************************************************************************************/

    // 캠핑장 현황 페이지 요청(GET)
    @GetMapping("/camp/current")
    public String campCurrentSearch(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "") String keyword, Model model) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        model.addAttribute("sessionUser", sessionUser);


        // 페이지당 게시물 수 상수로 고정
        final int PAGESIZE = 5;

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
        final int PAGESIZE = 5;

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
        model.addAttribute("sessionUser", sessionUser);

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
        model.addAttribute("sessionUser", sessionUser);


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

    // 환불 목록 페이지(GET)
    @GetMapping("/user/refund")
    public String refundSearch(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "") String keyword, Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        model.addAttribute("sessionUser", sessionUser);

        // 페이지당 게시물 수 상수로 고정
        final int PAGESIZE = 5;

        // 전체목록
        int refundAllSize = adminService.refundList(keyword).size();

        // 페이징목록
        List<AdminRespDTO.RefundDTO> refundDTOList = adminService.refundSearch(page, keyword, PAGESIZE);

        model.addAttribute("refundDTOList", refundDTOList);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("keyword", keyword);
        model.addAttribute("first", page == 0);
        model.addAttribute("last",
                (refundAllSize / PAGESIZE) == page
                        || ((refundAllSize % PAGESIZE == 0) && (refundAllSize / PAGESIZE) - 1 == page));
        return "admin/user_refund";
    }

    /******************************************************************************************/

    // 배너 등록 페이지 요청(GET)
    @GetMapping("/camp/banner")
    public String bannerPage(@RequestParam(defaultValue = "0") Integer page, Model model){
        User sessionUser = (User) session.getAttribute("sessionUser");
        model.addAttribute("sessionUser", sessionUser);

        // 페이지당 게시물 수 상수로 고정
        final int PAGESIZE = 5;

        // 전체목록
        int bannerAllSize = adminService.bannerList().size();

        // 페이징목록
        List<AdminRespDTO.BannerDTO> bannerDTOList = adminService.bannerPaging(page, PAGESIZE);

        model.addAttribute("bannerDTOList", bannerDTOList);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("first", page == 0);
        model.addAttribute("last",
                (bannerAllSize / PAGESIZE) == page
                        || ((bannerAllSize % PAGESIZE == 0) && (bannerAllSize / PAGESIZE) - 1 == page));
        return "admin/camp_banner";
    }

    // 배너 등록(POST)
    @PostMapping("/camp/banner/save")
    public String saveBanner(@ModelAttribute AdminReqDTO.SaveBannerDTO requestDTO){
        adminService.saveBanner(requestDTO);
        return "redirect:/admin/camp/banner";
    }
}
