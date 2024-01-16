package com.example.team_project.notice._dto;

import com.example.team_project.notice.Notice;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class NoticeRespDTO {

    // ME 공지사항 페이지 요청
    @Data
    public static class NoticeListDTO {
        private List<NoticeDTO> noticeDTOList;

        public NoticeListDTO(List<Notice> noticeList) {
            this.noticeDTOList = noticeList.stream().map(NoticeDTO::new).collect(Collectors.toList());
        }

        @Data
        public static class NoticeDTO {
            private Integer noticeId;
            private String title;
            private String content;
            private String createdAt;

            public NoticeDTO(Notice notice) {
                this.noticeId = notice.getId();
                this.title = notice.getTitle();
                this.content = notice.getContent();
                this.createdAt = notice.formatCreatedAt();
            }
        }
    }

}
