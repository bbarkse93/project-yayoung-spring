package com.example.team_project.camp.camp_review;

import com.example.team_project._core.utils.TimestampUtils;
import com.example.team_project.camp.Camp;
import com.example.team_project.camp.camp_rating.CampRating;
import com.example.team_project.order.Order;
import com.example.team_project.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "camp_review_tb")
public class CampReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private String reviewImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Camp camp;
    
    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CampRating campRating;

    private Timestamp createdAt;

    @Builder
    public CampReview(Integer id, String content, String reviewImage, User user, Camp camp, Timestamp createdAt, CampRating campRating) {
        this.id = id;
        this.content = content;
        this.reviewImage = reviewImage;
        this.user = user;
        this.camp = camp;
        this.createdAt = createdAt;
        this.campRating = campRating;
    }

    public String formatTime(){
        return TimestampUtils.timeStampToDate(createdAt);
    }
}
