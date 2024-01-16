package com.example.team_project.camp;

import java.text.DecimalFormat;
import java.util.List;

import com.example.team_project.camp.camp_image.CampImage;
import com.example.team_project.camp.camp_rating.CampRating;
import com.example.team_project.camp_field.CampField;

import com.example.team_project.camp_field.CampField;
import com.example.team_project.option.Option;
import com.example.team_project.option_management.OptionManagement;
import com.example.team_project.order.Order;
import jakarta.persistence.*;
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

    private boolean isDelete;

    @OneToMany(mappedBy = "camp", fetch = FetchType.LAZY)
    private List<CampImage> campImageList;

    @OneToMany(mappedBy = "camp", fetch = FetchType.LAZY)
    private List<CampRating> campRatingList;

    @OneToMany(mappedBy = "camp", fetch = FetchType.LAZY)
    private List<CampField> campFieldList;

    @OneToMany(mappedBy = "camp", fetch = FetchType.LAZY)
    private List<OptionManagement> optionManagementList;

    @Builder
    public Camp(Integer id, String campName, String campAddress, String campCallNumber, String campWebsite,
            String campRefundPolicy, boolean campWater, boolean campGarbageBag, String holiday, String campCheckIn,
            String campCheckOut, String campFieldImage, boolean isDelete) {
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
        this.isDelete = isDelete;
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

            double averageRating = campTotalRatingSum / campRatingList.size();
            System.out.println("평균 평점 ::: " + averageRating);
            DecimalFormat decimalFormat = new DecimalFormat("#.0"); // 소수점 첫째 자리까지 표시
            formatRating = decimalFormat.format(averageRating);
            System.out.println("포맷팅 평균 평점 ::: " + formatRating);
        } else {
            formatRating = "평가없음";
        }
        return formatRating;
    }

    // camp 각 평점
    public String formatRating(List<Double> ratings) {
        String formatRating;
        if(!campRatingList.isEmpty()) {
            double totalRating = 0;
            for(double rating : ratings) {
                totalRating += rating;
            }
            double avrageRating = totalRating / ratings.size();
            DecimalFormat decimalFormat = new DecimalFormat("#.0"); // 소수점 첫째 자리까지 표시
            formatRating = decimalFormat.format(avrageRating);
        }else{
            formatRating = "0.0";
        }
        return formatRating;
    }

    // 캠핑 삭제 업데이트
    public void updateIsDelete(boolean isDelete) {this.isDelete = isDelete;}



}
