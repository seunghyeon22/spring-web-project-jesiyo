package com.metacoding.web_project.report;

import lombok.Data;

public class ReportResponse {

    @Data
    public static class ReportDTO {
        private String sellerName = "";
        private String buyerName = "";
        private String title;
        private String reason;
        private String status = "대기중";



        public ReportDTO(Report report) {

            if (report.getReporter().getId() == report.getTransaction().getSeller().getId()) {
                sellerName += "(신고자)";
            } else {
                buyerName += "(신고자)";
            }

            this.sellerName += report.getTransaction().getSeller().getName();
            this.buyerName += report.getTransaction().getBuyer().getName();
            this.title = report.getTransaction().getGoods().getTitle();
            this.reason = report.getReason();

            if (report.getStatus() == 1) {
                this.status = "처리완료";
            }
        }
    }
}
