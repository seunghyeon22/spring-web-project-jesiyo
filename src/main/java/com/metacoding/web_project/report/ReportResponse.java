package com.metacoding.web_project.report;

import lombok.Data;

public class ReportResponse {

    @Data
    public static class ReportDTO {
        private Integer reportId;
        private String sellerName = "";
        private String buyerName = "";
        private String title;
        private String reason;
        private String status = "대기중";
        private String sellerPhone;
        private String buyerPhone;
        private boolean available = false;

        public ReportDTO(Report report) {

            if (report.getReporter().getId() == report.getTransaction().getSeller().getId()) {
                sellerName += "[신고자]";
            } else {
                buyerName += "[신고자]";
            }
            this.reportId = report.getId();
            this.sellerName += report.getTransaction().getSeller().getName();
            this.buyerName += report.getTransaction().getBuyer().getName();
            this.title = report.getTransaction().getGoods().getTitle();
            this.reason = report.getReason();
            this.sellerPhone = report.getTransaction().getSeller().getTel();
            this.buyerPhone = report.getTransaction().getBuyer().getTel();
            if (report.getStatus() == 0) {
                available = true;
            }
            if (report.getStatus() == 1) {
                this.status = "처리완료 (경매취소)";
            }
            if (report.getStatus() == 2) {
                this.status = "처리완료 (판매자에게 입찰금 전달)";
            }
        }
    }
}
