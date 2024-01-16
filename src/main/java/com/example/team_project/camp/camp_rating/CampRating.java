package com.example.team_project.camp.camp_rating;

import com.example.team_project.camp.Camp;
import com.example.team_project.camp.camp_review.CampReview;
import com.example.team_project.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.text.DecimalFormat;

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

    @Builder
<<<<<<< HEAD
    public CampRating(Integer id, double cleanliness, double managementness, double friendliness, 
    User user, Camp camp, CampReview campReview) {
=======
    public CampRating(Integer id, Integer cleanliness, Integer managementness, Integer friendliness, User user, Camp camp) {
>>>>>>> 6344634c5539a5c985b0243000354785ef2821c8
        this.id = id;
        this.cleanliness = cleanliness;
        this.managementness = managementness;
        this.friendliness = friendliness;
        this.user = user;
        this.camp = camp;
    }

    public double total () {
        return ((cleanliness + managementness + friendliness) / 3.0);
    }

<<<<<<< HEAD
    public void setCleanliness(double cleanliness) {
    }

    public void setManagementness(double managementness) {
    }

    public void setFriendliness(double friendliness) {
=======
    public String formatTotal(){
        DecimalFormat decimalFormat = new DecimalFormat("#.#"); // 소수점 첫째 자리까지 표시
        return decimalFormat.format(total());
>>>>>>> 6344634c5539a5c985b0243000354785ef2821c8
    }
}
