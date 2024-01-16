package com.example.team_project.admin;

import com.example.team_project._core.errors.exception.Exception401;
import com.example.team_project._core.errors.exception.Exception404;
import com.example.team_project.admin._dto.AdminReqDTO;
import com.example.team_project.admin._dto.AdminRespDTO;
import com.example.team_project.board.Board;
import com.example.team_project.board.BoardJPARepository;
import com.example.team_project.board.board_category.BoardCategory;
import com.example.team_project.board.board_category.BoardCategoryJPARepository;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.CampJPARepository;
import com.example.team_project.camp.camp_review.CampReview;
import com.example.team_project.camp.camp_review.CampReviewJPARepository;
import com.example.team_project.notice.Notice;
import com.example.team_project.notice.NoticeJPARepository;
import com.example.team_project.order.Order;
import com.example.team_project.order.OrderJPARepository;
import com.example.team_project.user.User;
import com.example.team_project.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class AdminService {

    private final CampJPARepository campJPARepository;
    private final CampReviewJPARepository campReviewJPARepository;
    private final UserJPARepository userJPARepository;
    private final OrderJPARepository orderJPARepository;
    private final BoardJPARepository boardJPARepository;
    private final BoardCategoryJPARepository boardCategoryJPARepository;
    private final NoticeJPARepository noticeJPARepository;

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
    public String saveNotice(AdminReqDTO.SaveNoticeDTO requestDTO) {
        User user = userJPARepository.findById(requestDTO.getUserId()).orElseThrow(() -> new Exception401("해당 유저를 찾을 수 없습니다." + requestDTO.getUserId()));
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
    public String updateNotice(AdminReqDTO.UpdateNoticeDTO requestDTO, Integer noticeId) {
        User user = userJPARepository.findById(requestDTO.getUserId()).orElseThrow(() -> new Exception401("해당 유저를 찾을 수 없습니다." + requestDTO.getUserId()));
        Notice notice = noticeJPARepository.findById(noticeId).orElseThrow(() -> new Exception404("해당 공지사항을 찾을 수 없습니다." + noticeId));
        notice.updateTitle(requestDTO.getTitle());
        notice.updateContent(requestDTO.getContent());
        notice.updateCreatedAt(new Timestamp(System.currentTimeMillis()));
        notice.updateUser(user);
        return "수정에 성공했습니다.";
    }
}