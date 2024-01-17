package com.example.team_project.order;

import com.example.team_project._core.utils.PriceUtils;
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

    private boolean isRefund; // true -> 환불

    @CreationTimestamp
    private Timestamp createdAt; // -> 환불 시 환불 시간으로 변경하기

    @Builder
    public Order(Integer id, Timestamp checkOutDate, Timestamp checkInDate, User user, CampField campField, boolean isRefund, Timestamp createdAt) {
        this.id = id;
        this.checkOutDate = checkOutDate;
        this.checkInDate = checkInDate;
        this.user = user;
        this.campField = campField;
        this.isRefund = isRefund;
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

    // 업데이트
    public void updateRefund(boolean isRefund){
        this.isRefund = isRefund;
    }

    public void updateRefundAt(Timestamp createdAt){
        this.createdAt = createdAt;
    }
}
