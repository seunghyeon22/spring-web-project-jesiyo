package com.metacoding.web_project.transaction;

import com.metacoding.web_project._core.util.FormatDate;
import lombok.Data;

public class TransactionResponse {

    @Data
    public static class TransactionDTO {
        private String title;
        private String sellerName;
        private String buyerName;
        private String success_price;
        private String update_at;
        private String sellerStatus = "대기중";
        private String buyerStatus = "대기중";


        public TransactionDTO(Transaction transaction) {


            this.title = transaction.getGoods().getTitle();
            this.sellerName = transaction.getSeller().getName();
            this.buyerName = transaction.getBuyer().getName();
            this.success_price = transaction.getSuccessPrice().toString();
            this.update_at = FormatDate.formatToyyyypMMpdd(transaction.getUpdatedAt());

            if (transaction.getSellerStatus() == 1) {
                this.sellerStatus = "판매확정";
            }
            if (transaction.getBuyerStatus() == 1) {
                this.buyerStatus = "구매확정";
            }
        }
    }
}
