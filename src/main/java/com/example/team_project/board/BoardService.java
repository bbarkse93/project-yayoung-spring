package com.example.team_project.board;

import com.example.team_project.board._dto.BoardRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardJPARepository boardJPARepository;

    // ME FAQ 페이지 요청
    public BoardRespDTO.BoardListDTO boardPage() {
        List<Board> paymentList = boardJPARepository.findByBoardCategoryId(1);
        List<Board> userList = boardJPARepository.findByBoardCategoryId(2);
        return new BoardRespDTO.BoardListDTO(paymentList, userList);
    }
}
