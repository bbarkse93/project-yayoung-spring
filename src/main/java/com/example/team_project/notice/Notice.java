package com.example.team_project.notice;

import com.example.team_project._core.utils.TimestampUtils;
import com.example.team_project.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "notice_tb")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Notice(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }

    // createdAt 포맷(yyyy-mm-dd)
    public String formatCreatedAt(){
        return TimestampUtils.timeStampToDate(createdAt);
    }

    // title 업데이트
    public void updateTitle(String title){
        this.title = title;
    }

    // content 업데이트
    public void updateContent(String content){
        this.content = content;
    }

    // createdAt 업데이트
    public void updateCreatedAt(Timestamp timestamp){
        this.createdAt = timestamp;
    }

    // user 업데이트
    public void updateUser(User user){
        this.user = user;
    }
}
