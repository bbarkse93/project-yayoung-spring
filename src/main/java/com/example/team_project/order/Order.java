package com.example.team_project.order;

import com.example.team_project._core.utils.TimestampUtils;
import com.example.team_project.camp_field.CampField;
import com.example.team_project.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "order_tb")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Timestamp checkOutDate;

    private Timestamp checkInDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private CampField campField;    

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Order(Integer id, Timestamp checkOutDate, Timestamp checkInDate, User user, CampField campField, Timestamp createdAt) {
        this.id = id;
        this.checkOutDate = checkOutDate;
        this.checkInDate = checkInDate;
        this.user = user;
        this.campField = campField;
        this.createdAt = createdAt;
    }

    public String formatCheckInDate(){
        return TimestampUtils.timeStampToDate(checkInDate);
    }

    public String formatCheckOutDate(){
        return TimestampUtils.timeStampToDate(checkOutDate);
    }

    public String formatCreatedAt(){
        return TimestampUtils.timeStampToDate(createdAt);
    }
}
