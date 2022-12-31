package com.epay.EPayment.Models;

public class Refund {
    static int serial;
    Transaction transaction;
    RefundState refundState;
    int id;

    public Refund(Transaction transaction) {
        this.transaction = transaction;
        refundState = RefundState.PENDING;
        id = ++serial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
