package com.example.team_project.camp.camp_bookmark;

import com.example.team_project.camp.Camp;
import com.example.team_project.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "camp_bookmark_tb")
public class CampBookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Camp camp;

    @Builder
    public CampBookmark(Integer id, User user, Camp camp) {
        this.id = id;
        this.user = user;
        this.camp = camp;
    }
}
