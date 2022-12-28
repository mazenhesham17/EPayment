package com.epay.EPayment.View;

import com.epay.EPayment.Models.Refund;

public class RefundView {
    static RefundView refundView = null;
    Refund refund;

    private RefundView() {

    }

    public static RefundView getInstance() {
        if (refundView == null)
            refundView = new RefundView();
        return refundView;
    }

    public void setRefund(Refund refund) {
        this.refund = refund;
    }

    public void showRefundDetails() {
        System.out.println("Transaction ID : " + refund.getTransaction().getId());
        System.out.println("Refund State : " + refund.getRefundState());
    }
}
