package com.example.team_project.user;


import com.example.team_project._core.utils.TimestampUtils;
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
@Table(name = "user_tb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String nickname;

    private String userImage;

    private boolean role; // user = true | admin = false

    private boolean isWithdraw;

    @CreationTimestamp
    private Timestamp userCreatedAt;

    @Builder
    public User(Integer id, String username, String password, String nickname, String userImage, boolean role, boolean isWithdraw, Timestamp userCreatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.userImage = userImage;
        this.role = role;
        this.isWithdraw = isWithdraw;
        this.userCreatedAt = userCreatedAt;
    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }
    public void updateUserImage(String userImage){
        this.userImage = userImage;
    }
    public void updateIsWithDraw(boolean isWithdraw) {this.isWithdraw = isWithdraw;}

    public String formatCreateAt(){
        return TimestampUtils.timeStampToDate(userCreatedAt);
    }

}