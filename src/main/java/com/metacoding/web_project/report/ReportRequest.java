package com.metacoding.web_project.report;

import com.metacoding.web_project.transaction.Transaction;
import com.metacoding.web_project.user.User;
import lombok.Data;

public class ReportRequest {

    // 신고하기 DTO
    @Data
    public static class ReportSaveDTO {
        private Integer transaction;
        private Integer reporter;
        private Integer reported;
        private Integer status;
        private String reason;

        public Report toEntity(User reporter, User reported) {
            return Report.builder()
                    .transaction(Transaction.builder().id(transaction).build())
                    .reporter(reporter)
                    .reported(reported)
                    .status(0)
                    .reason(reason)
                    .build();
        }
    }
}
