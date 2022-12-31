package com.epay.EPayment.Transaction;

import com.epay.EPayment.Models.Customer;
import com.epay.EPayment.Models.Refund;

public class RefundTransaction extends Transaction {
    Refund refund;
    int refundedID;

    public RefundTransaction(Customer customer, double amount, Refund refund) {
        super(customer, amount);
        super.setTransactionType("Refund Transaction");
        super.setPaymentMethod("Refund");
        super.setRequested(true);
        this.refund = refund;
        refundedID = refund.getTransaction().getId();
    }

    public Refund getRefund() {
        return refund;
    }

    public int getRefundedID() {
        return refundedID;
    }
}
