package com.epay.EPayment.Models;

public class Refund {
    Transaction transaction;
    RefundState refundState;

    public Refund(Transaction transaction) {
        this.transaction = transaction;
        refundState = RefundState.PENDING;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Customer getCustomer() {
        return transaction.getCustomer();
    }

    public RefundState getRefundState() {
        return refundState;
    }

    public void setRefundState(RefundState refundState) {
        this.refundState = refundState;
    }

}
