package com.example.team_project.admin._dto;

import com.example.team_project.camp.Camp;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

public class AdminRespDTO {


    @Data
    public static class CampListDTO {
        private List<CampDTO> campDTOList;

        public CampListDTO(List<Camp> campList) {
            this.campDTOList = campList.stream().map(CampDTO::new).collect(Collectors.toList());;
        }

        @Data
        public static class CampDTO {
            private Integer campId;
            private String campName;
            private String campAddress;
            private String campNumber;

            public CampDTO(Camp camp) {
                this.campId = camp.getId();
                this.campName = camp.getCampName();
                this.campAddress = camp.getCampAddress();
                this.campNumber = camp.getCampCallNumber();
            }
        }
    }

}
