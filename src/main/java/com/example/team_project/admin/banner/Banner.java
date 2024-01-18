package com.example.team_project.admin.banner;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "banner_tb")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bannerImage;

    @Builder
    public Banner(Integer id, String bannerImage) {
        this.id = id;
        this.bannerImage = bannerImage;
    }

    // 파일명만 얻기
    public String formatFileName(){
        String[] formatList = bannerImage.split("/");
        return formatList[3];
    }
}
