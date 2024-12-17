package com.metacoding.web_project.report;

import lombok.Data;

public class ReportRequest {

    // 신고하기 DTO
    @Data
    public static class ReportSaveDTO {
        private Integer transactionId;
        private String reason;

    }
}
