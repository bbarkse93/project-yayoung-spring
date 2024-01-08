package com.example.team_project.board._dto;

import com.example.team_project.board.Board;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BoardRespDTO {

    // ME FAQ 페이지 요청
    @Data
    public static class BoardListDTO {
        private List<PaymentDTO> paymentDTOList;
        private List<UserDTO> userDTOList;

        public BoardListDTO(List<Board> paymentList, List<Board> userList) {
            this.paymentDTOList = paymentList.stream().map(PaymentDTO::new).collect(Collectors.toList());
            this.userDTOList = userList.stream().map(UserDTO::new).collect(Collectors.toList());
        }

        @Data
        public static class PaymentDTO {
            private Integer faqId;
            private String title;
            private String content;

            public PaymentDTO(Board board) {
                this.faqId = board.getId();
                this.title = board.getTitle();
                this.content = board.getContent();
            }
        }

        @Data
        public static class UserDTO {
            private Integer faqId;
            private String title;
            private String content;

            public UserDTO(Board board) {
                this.faqId = board.getId();
                this.title = board.getTitle();
                this.content = board.getContent();
            }
        }
    }
}
