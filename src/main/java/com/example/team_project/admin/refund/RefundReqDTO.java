package com.example.team_project.admin.refund;

import lombok.Data;

public class RefundReqDTO {

    @Data
    public static class RefundRequestDTO{
        private Integer orderId;
        private String merchantUid;
        private String cancelRequestAmount;
        private String reason;
    }
}
