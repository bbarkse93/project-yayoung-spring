package com.example.team_project.camp;

import java.util.List;

import com.example.team_project.camp.camp_image.CampImage;
import com.example.team_project.camp.camp_rating.CampRating;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "camp_tb")
public class Camp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String campName;

    @Column(length = 100, nullable = false)
    private String campAddress;

    @Column(length = 50, nullable = false)
    private String campCallNumber;

    private String campWebsite;

    private String campRefundPolicy;

    private boolean campWater;

    private boolean campGarbageBag;

    private String holiday;

    private String campCheckIn;

    private String campCheckOut;

    private String campFieldImage;

    @OneToMany(mappedBy = "camp", fetch = FetchType.LAZY)
    private List<CampImage> campImageList;

    @OneToMany(mappedBy = "camp", fetch = FetchType.LAZY)
    private List<CampRating> campRatingList;

    @Builder
    public Camp(Integer id, String campName, String campAddress, String campCallNumber, String campWebsite,
            String campRefundPolicy, boolean campWater, boolean campGarbageBag, String holiday, String campCheckIn,
            String campCheckOut, String campFieldImage) {
        this.id = id;
        this.campName = campName;
        this.campAddress = campAddress;
        this.campCallNumber = campCallNumber;
        this.campWebsite = campWebsite;
        this.campRefundPolicy = campRefundPolicy;
        this.campWater = campWater;
        this.campGarbageBag = campGarbageBag;
        this.holiday = holiday;
        this.campCheckIn = campCheckIn;
        this.campCheckOut = campCheckOut;
        this.campFieldImage = campFieldImage;
    }

    // camp 대표 이미지
    public String firstCampImage() {
        return campImageList.get(0).getCampImage();
    }

    // camp의 총 평점
    public String formatTotalRating() {
        String formatRating;
        if (!campRatingList.isEmpty()) {
            double campTotalRatingSum = 0;
            for (CampRating campRating : campRatingList) {
                campTotalRatingSum += campRating.total();
            }
            formatRating = String.valueOf(campTotalRatingSum / campRatingList.size());
        } else {
            formatRating = "평가없음";
        }
        return formatRating;
    }
}
