package com.example.team_project.camp.camp_rating;

import com.example.team_project.camp.Camp;
import com.example.team_project.camp.camp_review.CampReview;
import com.example.team_project.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "camp_rating_tb")
public class CampRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double cleanliness;
    private double managementness;
    private double friendliness;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Camp camp;

    @OneToOne(fetch = FetchType.LAZY)
    private CampReview campReview;

    @Builder
    public CampRating(Integer id, Integer cleanliness, Integer managementness, Integer friendliness, User user, Camp camp, CampReview campReview) {
        this.id = id;
        this.cleanliness = cleanliness;
        this.managementness = managementness;
        this.friendliness = friendliness;
        this.user = user;
        this.camp = camp;
        this.campReview = campReview;
    }

    public double total () {
        return ((cleanliness + managementness + friendliness) / 3.0);
    }
}
