package com.example.team_project.camp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Timestamp;

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

    private Timestamp campCheckIn;

    private Timestamp campCheckOut;

    private String campFieldImage;

    @Builder
    public Camp(Integer id, String campName, String campAddress, String campCallNumber, String campWebsite, String campRefundPolicy, boolean campWater, boolean campGarbageBag, String holiday, Timestamp campCheckIn, Timestamp campCheckOut, String campFieldImage) {
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
}
