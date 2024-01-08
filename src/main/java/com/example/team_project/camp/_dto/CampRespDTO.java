package com.example.team_project.camp._dto;

import lombok.Data;

public class CampRespDTO {

    @Data
    public static class CampListDTO {
        private Integer id;
        private String campName;
        private String campAddress;
        private String campCallNumber;
        private String campWebsite;
        private String campRefundPolicy;
        private boolean campWater;
        private boolean campGarbageBag;
        private String holiday;
        private String campCheckIn;
        private String campCheckOut;
        private String campFieldImage;

    public CampListDTO(Integer id, String campName, String campAddress, String campCallNumber, String campWebsite,
                       String campRefundPolicy, boolean campWater, boolean campGarbageBag, String holiday, 
                       String campCheckIn, String campCheckOut, String campFieldImage) {
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
}
