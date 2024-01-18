package com.example.team_project.admin;

import com.example.team_project._core.errors.exception.CustomRestfullException;
import com.example.team_project._core.errors.exception.Exception401;
import com.example.team_project._core.errors.exception.Exception404;
import com.example.team_project._core.utils.ImageUtils;
import com.example.team_project.admin._dto.AdminReqDTO;
import com.example.team_project.admin._dto.AdminRespDTO;
import com.example.team_project.board.Board;
import com.example.team_project.board.BoardJPARepository;
import com.example.team_project.board.board_category.BoardCategory;
import com.example.team_project.board.board_category.BoardCategoryJPARepository;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.CampJPARepository;
import com.example.team_project.camp.camp_image.CampImage;
import com.example.team_project.camp.camp_image.CampImageJPARepository;
import com.example.team_project.camp.camp_review.CampReview;
import com.example.team_project.camp.camp_review.CampReviewJPARepository;
import com.example.team_project.camp_field.CampField;
import com.example.team_project.camp_field.CampFieldJPARepository;
import com.example.team_project.notice.Notice;
import com.example.team_project.notice.NoticeJPARepository;
import com.example.team_project.option.Option;
import com.example.team_project.option.OptionJPARepository;
import com.example.team_project.option_management.OptionManagement;
import com.example.team_project.option_management.OptionManagementJPARepository;
import com.example.team_project.order.Order;
import com.example.team_project.order.OrderJPARepository;
import com.example.team_project.user.User;
import com.example.team_project.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class AdminService {

    private final CampJPARepository campJPARepository;
    private final CampReviewJPARepository campReviewJPARepository;
    private final CampFieldJPARepository campFieldJPARepository;
    private final CampImageJPARepository campImageJPARepository;
    private final UserJPARepository userJPARepository;
    private final OrderJPARepository orderJPARepository;
    private final OptionManagementJPARepository optionManagementJPARepository;
    private final OptionJPARepository optionJPARepository;
    private final BoardJPARepository boardJPARepository;
    private final BoardCategoryJPARepository boardCategoryJPARepository;
    private final NoticeJPARepository noticeJPARepository;

    // 로그인
    public User login(AdminReqDTO.LoginDTO dto) {
        User user = userJPARepository.findByUsername(dto.getUsername());
        System.out.println("조회 완료 : ");

        // 유저 정보 확인
        if (user == null) {
                throw new CustomRestfullException("유저 정보가 없습니다.", HttpStatus.BAD_REQUEST);
        }

        if (user.isRole() != false) {
             throw new CustomRestfullException("관리자 권한이 없습니다." , HttpStatus.BAD_REQUEST);
        }

        // 유저 네임 확인
        if (!user.getUsername().equals(dto.getUsername()) || user.getUsername().isEmpty()){
           throw new CustomRestfullException("username 정보가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 패스워드 확인
        if (!user.getPassword().equals(dto.getPassword()) || user.getPassword().isEmpty()) {
            throw new CustomRestfullException("password 정보가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
        return user;

    }

    // 캠핑장 목록(캠핑장 수)
    public List<AdminRespDTO.CampDTO> campList(String keyword) {
        List<Camp> campList = campJPARepository.mfindSearchAll(keyword);
        return campList.stream().map(AdminRespDTO.CampDTO::new).collect(Collectors.toList());
    }

    // 캠핑장 목록 페이징(페이징 된 화면 수)
    public List<AdminRespDTO.CampDTO> campSearch(Integer page, String keyword, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
        Page<Camp> campPG = campJPARepository.mfindSearchPageAll(keyword, pageable);
        return campPG.stream().map(AdminRespDTO.CampDTO::new).collect(Collectors.toList());
    }

    // 캠핑장 삭제
    @Transactional
    public String deleteCamp(Integer campId) {
        Camp camp = campJPARepository.findById(campId).orElseThrow(() -> new Exception404("해당 캠핑장을 찾을 수 없습니다." + campId));
        camp.updateIsDelete(true);
        return "삭제에 성공했습니다.";
    }

    // 캠핑장 리뷰 리스트(모달)
    public AdminRespDTO.CampReviewDTO campReviewList(Integer campId) {
        Camp camp = campJPARepository.findById(campId).orElseThrow(() -> new Exception404("해당 캠핑장을 찾을 수 없습니다." + campId));
        List<CampReview> campReviewList = campReviewJPARepository.findAllByCampId(campId);
        return new AdminRespDTO.CampReviewDTO(camp, campReviewList);
    }

    // 캠핑장 현황 목록(캠핑장 수)
    public List<AdminRespDTO.RatingCampDTO> ratingCampList(String keyword) {
        List<Camp> campList = campJPARepository.mfindSearchAll(keyword);
        return campList.stream().map(AdminRespDTO.RatingCampDTO::new).collect(Collectors.toList());
    }

    // 캠핑장 현황 목록 페이징(페이징 된 화면 수)
    public List<AdminRespDTO.RatingCampDTO> ratingCampSearch(Integer page, String keyword, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
        Page<Camp> campPG = campJPARepository.mfindSearchPageAll(keyword, pageable);
        return campPG.stream().map(AdminRespDTO.RatingCampDTO::new).collect(Collectors.toList());
    }

    // 캠핑장 리뷰 삭제
    @Transactional
    public String deleteReview(Integer reviewId) {
        CampReview campReview = campReviewJPARepository.findById(reviewId).orElseThrow(() -> new Exception404("해당 리뷰를 찾을 수 없습니다." + reviewId));
        campReviewJPARepository.deleteById(campReview.getId());
        return "삭제에 성공했습니다.";
    }

    // 유저 목록(유저 수)
    public List<AdminRespDTO.UserDTO> userList(String keyword) {
        List<User> userList = userJPARepository.mfindSearchAll(keyword);
        return userList.stream().map(AdminRespDTO.UserDTO::new).collect(Collectors.toList());
    }

    // 유저 목록 페이징(페이징 된 화면 수)
    public List<AdminRespDTO.UserDTO> userSearch(Integer page, String keyword, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
        Page<User> userPG = userJPARepository.mfindSearchPageAll(keyword, pageable);
        return userPG.stream().map(AdminRespDTO.UserDTO::new).collect(Collectors.toList());
    }

    // 유저 삭제
    @Transactional
    public String deleteUser(Integer userId) {
        User user = userJPARepository.findById(userId).orElseThrow(() -> new Exception401("해당 유저를 찾을 수 없습니다." + userId));
        user.updateIsWithDraw(true);
        return "삭제에 성공했습니다.";
    }

    // 유저 상세 정보
    public AdminRespDTO.UserDetailDTO detailUser(Integer userId) {
        User user = userJPARepository.findById(userId).orElseThrow(()-> new Exception401("해당 유저를 찾을 수 없습니다." + userId));
        List<Order> orderList = orderJPARepository.findAllByUserId(user.getId());
        List<CampReview> campReviewList = campReviewJPARepository.findAllByUserId(user.getId());
        return new AdminRespDTO.UserDetailDTO(user, orderList, campReviewList);
    }

    // faq 목록(faq 갯수)
    public AdminRespDTO.FaqDTOList faqList(String keyword) {
        List<Board> boardList = boardJPARepository.mfindSearchAll(keyword);
        return new AdminRespDTO.FaqDTOList(boardList);
    }

    // faq 목록 페이징(페이징 된 화면 수)
    public AdminRespDTO.FaqDTOList faqSearch(Integer page, String keyword, Integer pageSize, Integer categoryId) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
        Page<Board> boardPG = boardJPARepository.mfindSearchPageAll(keyword, pageable, categoryId);
        List<Board> boardList = boardPG.getContent();
        return new AdminRespDTO.FaqDTOList(boardList);
    }

    // faq 상세보기
    public AdminRespDTO.FaqDetailDTO detailFaq(Integer faqId) {
        Board board = boardJPARepository.findById(faqId).orElseThrow(()-> new Exception404("해당 FAQ를 찾을 수 없습니다." + faqId));
        return new AdminRespDTO.FaqDetailDTO(board);
    }

    // faq 삭제
    @Transactional
    public String deleteFaq(Integer faqId) {
        Board board = boardJPARepository.findById(faqId).orElseThrow(() -> new Exception404("해당 FAQ를 찾을 수 없습니다." + faqId));
        boardJPARepository.deleteById(board.getId());
        return "삭제에 성공했습니다.";
    }

    // faq 등록
    @Transactional
    public String saveFaq(AdminReqDTO.SaveFaqDTO requestDTO) {
        User user = userJPARepository.findById(requestDTO.getUserId()).orElseThrow(() -> new Exception401("해당 유저를 찾을 수 없습니다." + requestDTO.getUserId()));
        BoardCategory boardCategory = boardCategoryJPARepository.findById(requestDTO.getBoardCategoryId()).orElseThrow(()->new Exception404("해당 카테고리를 찾을 수 없습니다." + requestDTO.getBoardCategoryId()));
        Board board = Board.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .user(user)
                .boardCategory(boardCategory)
                .build();
        boardJPARepository.save(board);
        return "등록에 성공했습니다.";
    }

    // faq 수정
    @Transactional
    public String updateFaq(AdminReqDTO.UpdateFaqDTO requestDTO, Integer faqId) {
        User user = userJPARepository.findById(requestDTO.getUserId()).orElseThrow(() -> new Exception401("해당 유저를 찾을 수 없습니다." + requestDTO.getUserId()));
        Board board = boardJPARepository.findById(faqId).orElseThrow(() -> new Exception404("해당 FAQ를 찾을 수 없습니다." + faqId));
        board.updateTitle(requestDTO.getTitle());
        board.updateContent(requestDTO.getContent());
        board.updateCreatedAt(new Timestamp(System.currentTimeMillis()));
        board.updateUser(user);
        return "수정에 성공했습니다.";
    }

    // notice 목록(faq 갯수)
    public List<AdminRespDTO.NoticeDTO> noticeList(String keyword) {
        List<Notice> noticeList = noticeJPARepository.mfindSearchAll(keyword);
        return noticeList.stream().map(AdminRespDTO.NoticeDTO::new).collect(Collectors.toList());
    }

    // notice 목록 페이징(페이징 된 화면 수)
    public List<AdminRespDTO.NoticeDTO> noticeSearch(Integer page, String keyword, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
        Page<Notice> campPG = noticeJPARepository.mfindSearchPageAll(keyword, pageable);
        return campPG.stream().map(AdminRespDTO.NoticeDTO::new).collect(Collectors.toList());
    }


    // faq 상세보기
    public AdminRespDTO.NoticeDetailDTO detailNotice(Integer noticeId) {
        Notice notice = noticeJPARepository.findById(noticeId).orElseThrow(()-> new Exception404("해당 FAQ를 찾을 수 없습니다." + noticeId));
        return new AdminRespDTO.NoticeDetailDTO(notice);
    }


    // notice 삭제
    @Transactional
    public String deleteNotice(Integer noticeId) {
        Notice notice = noticeJPARepository.findById(noticeId).orElseThrow(() -> new Exception404("해당 공지사항을 찾을 수 없습니다." + noticeId));
        noticeJPARepository.deleteById(notice.getId());
        return "삭제에 성공했습니다.";
    }

    // notice 등록
    @Transactional
    public String saveNotice(AdminReqDTO.SaveNoticeDTO requestDTO, User user) {
        Notice notice = Notice.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .user(user)
                .build();
        noticeJPARepository.save(notice);
        return "등록에 성공했습니다.";
    }

    // notice 수정
    @Transactional
    public String updateNotice(AdminReqDTO.UpdateNoticeDTO requestDTO, Integer noticeId, User user) {
        Notice notice = noticeJPARepository.findById(noticeId).orElseThrow(() -> new Exception404("해당 공지사항을 찾을 수 없습니다." + noticeId));
        notice.updateTitle(requestDTO.getTitle());
        notice.updateContent(requestDTO.getContent());
        notice.updateCreatedAt(new Timestamp(System.currentTimeMillis()));
        notice.updateUser(user);
        return "수정에 성공했습니다.";
    }

    // 캠핑장 등록 페이지 요청(옵션)
    public List<AdminRespDTO.OptionDTO> OptionList(){
        List<Option> optionList = optionJPARepository.findAll();
        return optionList.stream().map(AdminRespDTO.OptionDTO::new).collect(Collectors.toList());
    }

    // 캠핑장 등록
    @Transactional
    public Integer saveCamp(AdminReqDTO.SaveCampDTO requestDTO) {
        try {
            Camp camp = Camp.builder()
                    .campName(requestDTO.getCampName())
                    .campAddress(requestDTO.getCampAddress())
                    .campCallNumber(requestDTO.getCampCallNumber())
                    .campWebsite(requestDTO.getCampWebsite())
                    .campCheckIn(requestDTO.getCampCheckIn())
                    .campCheckOut(requestDTO.getCampCheckOut())
                    .campWater(requestDTO.isCampWater())
                    .holiday(requestDTO.getHoliday())
                    .campGarbageBag(requestDTO.isCampGarbageBag())
                    .campRefundPolicy(requestDTO.getCampRefundPolicy())
                    .campFieldImage(ImageUtils.formatCampFieldImage(requestDTO.getCampFieldImage()))
                    .build();
            Camp saveCamp = campJPARepository.save(camp);
            return saveCamp.getId();
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


    // 캠핑장 옵션 등록
    @Transactional
    public void saveOptionManagement(AdminReqDTO.SaveCampDTO requestDTO, Integer campId) {
        List<AdminReqDTO.SaveCampDTO.OptionDTO> selectOptionDTOList = new ArrayList<>();
        for(AdminReqDTO.SaveCampDTO.OptionDTO optionDTO : requestDTO.getCampOptionDTOList()) {
            if(optionDTO.getOptionId() != null){
                selectOptionDTOList.add(optionDTO);
            }
        }
        try {
            for(AdminReqDTO.SaveCampDTO.OptionDTO selectOptionDTO : selectOptionDTOList){
                Option option = optionJPARepository.findById(selectOptionDTO.getOptionId()).orElseThrow(() -> new Exception404("해당 옵션을 찾을 수 없습니다." + selectOptionDTO.getOptionId()));
                Camp camp = campJPARepository.findById(campId).orElseThrow(() -> new Exception404("해당 캠핑장을 찾을 수 없습니다." + campId));
                OptionManagement optionManagement = OptionManagement.builder()
                        .option(option)
                        .camp(camp)
                        .build();
                optionManagementJPARepository.save(optionManagement);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    // 캠핑장 구역 등록
    @Transactional
    public void saveCampField(AdminReqDTO.SaveCampDTO requestDTO, Integer campId) {
        try {
            for(AdminReqDTO.SaveCampDTO.CampFieldDTO campFieldDTO : requestDTO.getCampFieldDTOList()){
                Camp camp = campJPARepository.findById(campId).orElseThrow(() -> new Exception404("해당 캠핑장을 찾을 수 없습니다." + campId));
                CampField campField = CampField.builder()
                        .camp(camp)
                        .fieldName(campFieldDTO.getFieldName())
                        .price(campFieldDTO.getPrice())
                        .build();
                campFieldJPARepository.save(campField);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    // 캠핑장 이미지 등록
    @Transactional
    public void saveCampImage(AdminReqDTO.SaveCampDTO requestDTO, Integer campId) {
        try {
            for(MultipartFile campPhoto : requestDTO.getCampPhotoList()){
                Camp camp = campJPARepository.findById(campId).orElseThrow(() -> new Exception404("해당 캠핑장을 찾을 수 없습니다." + campId));
                CampImage campImage = CampImage.builder()
                        .camp(camp)
                        .campImage(ImageUtils.formatCampImage(campPhoto))
                        .build();
                campImageJPARepository.save(campImage);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    // 캠핑장 상세(모달)
    public AdminRespDTO.CampDetailDTO campDetail(Integer campId){
        Camp camp = campJPARepository.findById(campId).orElseThrow(() -> new Exception404("해당 캠핑장을 찾을 수 없습니다." + campId));
        return new AdminRespDTO.CampDetailDTO(camp);
    }

    // 캠핑장 수정
    @Transactional
    public void updateCamp(AdminReqDTO.UpdateCampDTO requestDTO, Integer campId) {
        Camp camp = campJPARepository.findById(campId).orElseThrow(() -> new Exception404("해당 캠핑장을 찾을 수 없습니다." + campId));
        try {
            camp.updateCampName(requestDTO.getCampName());
            camp.updateCampAddress(requestDTO.getCampAddress());
            camp.updateCampWebsite(requestDTO.getCampWebsite());
            camp.updateCampCallNumber(requestDTO.getCampCallNumber());
            camp.updateCampRefundPolicy(requestDTO.getCampRefundPolicy());
            camp.updateCampCheckIn(requestDTO.getCampCheckIn());
            camp.updateCampCheckOut(requestDTO.getCampCheckOut());
            camp.updateCampWater(requestDTO.isCampWater());
            camp.updateCampGarbageBag(requestDTO.isCampGarbageBag());
            camp.updateHoliday(requestDTO.getHoliday());
            camp.updateCampFieldImage(ImageUtils.formatCampFieldImage(requestDTO.getCampFieldImage()));
        } catch (Exception e) {
            e.getMessage();
        }
    }


    // 캠핑장 옵션 수정
    @Transactional
    public void updateOptionManagement(AdminReqDTO.UpdateCampDTO requestDTO, Integer campId) {
        List<AdminReqDTO.UpdateCampDTO.OptionDTO> selectOptionDTOList = new ArrayList<>();
        for(AdminReqDTO.UpdateCampDTO.OptionDTO optionDTO : requestDTO.getCampOptionDTOList()) {
            if(optionDTO.getOptionId() != null){
                selectOptionDTOList.add(optionDTO);
            }
        }
        try {
            // 기존 옵션들 호출
            List<OptionManagement> optionManagementList = optionManagementJPARepository.findAllByCampId(campId);
            // camp 정보 가져오기
            Camp camp = campJPARepository.findById(campId).orElseThrow(() -> new Exception404("해당 캠핑장을 찾을 수 없습니다." + campId));
            // 새 옵션 저장

//            for(AdminReqDTO.UpdateCampDTO.OptionDTO selectOptionDTO : selectOptionDTOList){
//                Option option = optionJPARepository.findById(selectOptionDTO.getOptionId()).orElseThrow(() -> new Exception404("해당 옵션을 찾을 수 없습니다." + selectOptionDTO.getOptionId()));
//                Camp camp = campJPARepository.findById(campId).orElseThrow(() -> new Exception404("해당 캠핑장을 찾을 수 없습니다." + campId));
//                OptionManagement optionManagement = OptionManagement.builder()
//                        .option(option)
//                        .camp(camp)
//                        .build();
//                optionManagementJPARepository.save(optionManagement);
//            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    // 캠핑장 구역 수정
    @Transactional
    public void updateCampField(AdminReqDTO.UpdateCampDTO requestDTO, Integer campId) {
        try {
            // 기존 구역 호출
            List<CampField> campFieldList = campFieldJPARepository.findAllByCampId(campId);

            // 기존 구역과 새로운 구역 비교
            List<CampField> differentObjectList = new ArrayList<>(); /// 다른 값만 받기

            differentObjectList = campFieldList.stream()
                    .filter(o -> requestDTO.getCampFieldDTOList().stream().noneMatch(n -> {return o.getId().equals(n.getFieldId()) && o.getFieldName().equals(n.getFieldName()) && o.getPrice().equals(n.getPrice());}))
                    .collect(Collectors.toList());




            System.out.println("==========================================");
            for(AdminReqDTO.UpdateCampDTO.CampFieldDTO after : requestDTO.getCampFieldDTOList()){
                System.out.println("after : " + after.getFieldName());
                System.out.println("after : " + after.getFieldId());
                System.out.println("after : " + after.getPrice());
                System.out.println("==========================================");
            }
            System.out.println("==========================================");
            for(CampField before : differentObjectList){
                System.out.println("before : " + before.getFieldName());
                System.out.println("before : " + before.getId());
                System.out.println("before : " + before.getPrice());
                System.out.println("==========================================");
            }


//            if()







//            // camp 정보 가져오기
//            Camp camp = campJPARepository.findById(campId).orElseThrow(() -> new Exception404("해당 캠핑장을 찾을 수 없습니다." + campId));
//            for(int i = 0; i < requestDTO.getCampFieldDTOList().size(); i++){
//                campFieldList.get(i).updateFieldName(requestDTO.getCampFieldDTOList().get(i).getFieldName());
//                if(campFieldList.get(i) != null){
//                    CampField campField = CampField.builder()
//                                                    .camp(camp)
//                                                    .fieldName(requestDTO.getCampFieldDTOList().get(i).getFieldName())
//                                                    .price(requestDTO.getCampFieldDTOList().get(i).getPrice())
//                                                    .build();
//                campFieldJPARepository.save(campField);
//                }
//            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    // 캠핑장 이미지 수정
    @Transactional
    public void updateCampImage(AdminReqDTO.UpdateCampDTO requestDTO, Integer campId) {
        try {
            if(requestDTO.getCampPhotoList() != null){
            for(MultipartFile campPhoto : requestDTO.getCampPhotoList()) {
                Camp camp = campJPARepository.findById(campId).orElseThrow(() -> new Exception404("해당 캠핑장을 찾을 수 없습니다." + campId));
                CampImage campImage = CampImage.builder()
                        .camp(camp)
                        .campImage(ImageUtils.formatCampImage(campPhoto))
                        .build();
                campImageJPARepository.save(campImage);
            }
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    // 환불 목록(캠핑장 수)
    public List<AdminRespDTO.RefundDTO> refundList(String keyword) {
        List<Order> refundList = orderJPARepository.mfindSearchAll(keyword);
        return refundList.stream().map(AdminRespDTO.RefundDTO::new).collect(Collectors.toList());
    }

    // 환불 목록 페이징(페이징 된 화면 수)
    public List<AdminRespDTO.RefundDTO> refundSearch(Integer page, String keyword, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
        Page<Order> orderPG = orderJPARepository.mfindSearchPageAll(keyword, pageable);
        return orderPG.stream().map(AdminRespDTO.RefundDTO::new).collect(Collectors.toList());
    }

    // 환불 상세
    public AdminRespDTO.RefundDetailDTO refundDetail(Integer orderId) {
        Order order = orderJPARepository.findById(orderId).orElseThrow(() -> new Exception401("해당 주문을 찾을 수 없습니다." + orderId));
        return new AdminRespDTO.RefundDetailDTO(order);
    }

    // 환불 등록
    @Transactional
    public String saveRefund(Integer orderId) {
        Order order = orderJPARepository.findById(orderId).orElseThrow(() -> new Exception404("해당 주문을 찾을 수 없습니다." + orderId));
        order.updateRefund(true);
        order.updateRefundAt(new Timestamp(System.currentTimeMillis()));
        return "환불이 완료되었습니다.";
    }

}