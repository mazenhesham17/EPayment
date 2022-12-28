package com.epay.EPayment.Controller;

import com.epay.EPayment.Models.Customer;
import com.epay.EPayment.Models.Refund;
import com.epay.EPayment.Models.RefundState;

public class RefundController {
    static RefundController refundController = null;
    Refund refund;

    private RefundController() {

    }

    public static RefundController getInstance() {
        if (refundController == null)
            refundController = new RefundController();
        return refundController;
    }

    public void setRefund(Refund refund) {
        this.refund = refund;
    }

    public RefundState getRefundState() {
        return refund.getRefundState();
    }

    public Customer getCustomer() {
        return refund.getCustomer();
    }

    public void acceptRefund() {
        refund.setRefundState(RefundState.ACCEPTED);
    }

    public void rejectRefund() {
        refund.setRefundState(RefundState.REJECTED);
    }
}
