package com.example.team_project.admin.banner;

import com.example.team_project._core.utils.TimestampUtils;
import com.example.team_project.board.board_category.BoardCategory;
import com.example.team_project.camp.Camp;
import com.example.team_project.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "banner_tb")
public class banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bannerImage;

    @Builder
    public banner(Integer id, String bannerImage) {
        this.id = id;
        this.bannerImage = bannerImage;
    }
}
