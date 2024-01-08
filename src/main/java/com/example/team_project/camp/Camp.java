package com.example.team_project.camp;

import com.example.team_project.camp.camp_image.CampImage;
import com.example.team_project.camp.camp_rating.CampRating;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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
    public String formatTotalRating () {
        String formatRating;
        if(!campRatingList.isEmpty()){
            double campTotalRatingSum = 0;
            for (CampRating campRating: campRatingList) {
                campTotalRatingSum+=campRating.total();
            }
            formatRating = String.valueOf(campTotalRatingSum/3);
        }else{
            formatRating = "평가없음";
        }
        return formatRating;
    }
}
