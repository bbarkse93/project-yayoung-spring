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

    private String orderNumber;

    private Timestamp checkOutDate;

    private Timestamp checkInDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private CampField campField;

    private Boolean isRefund; // true -> 환불

    @CreationTimestamp
    private Timestamp createdAt;

    @CreationTimestamp
    private Timestamp refundAt; // 환불 발생 시 변경됨 - 처음에는 결제 시간이랑 동일한 값이 들어감(null 허용 X라서)

    @Builder
    public Order(Integer id, String orderNumber, Timestamp checkOutDate, Timestamp checkInDate, User user, CampField campField, boolean isRefund, Timestamp createdAt, Timestamp refundAt) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.checkOutDate = checkOutDate;
        this.checkInDate = checkInDate;
        this.user = user;
        this.campField = campField;
        this.isRefund = isRefund;
        this.createdAt = createdAt;
        this.refundAt = refundAt;
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

    public String formatRefundAt(){
        return TimestampUtils.timeStampToDateAndTime(refundAt);
    }

    // 업데이트
    public void updateRefund(boolean isRefund){
        this.isRefund = isRefund;
    }

    public void updateRefundAt(Timestamp createdAt){
        this.createdAt = createdAt;
    }
}
