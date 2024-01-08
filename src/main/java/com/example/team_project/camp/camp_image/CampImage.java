package com.example.team_project.camp.camp_image;

import com.example.team_project.camp.Camp;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "camp_image_tb")
public class CampImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String campImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private Camp camp;

    @Builder
    public CampImage(Integer id, String campImage, Camp camp) {
        this.id = id;
        this.campImage = campImage;
        this.camp = camp;
    }

}
