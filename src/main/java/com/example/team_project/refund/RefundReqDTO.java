package com.example.team_project.refund;

import lombok.Data;

public class RefundReqDTO {

    @Data
    public static class RefundRequestDTO{
        private Integer orderId;
        private String orderNumber;
        private String refund;
        private Integer userId;

    }
}