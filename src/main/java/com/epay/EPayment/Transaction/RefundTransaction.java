package com.epay.EPayment.Transaction;

import com.epay.EPayment.Controller.RefundController;
import com.epay.EPayment.Controller.TransactionController;
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
        TransactionController transactionController = TransactionController.getInstance();
        RefundController refundController = RefundController.getInstance();
        refundController.setRefund(refund);
        transactionController.setTransaction(refundController.getTransaction());
        refundedID = transactionController.getId();
    }

    public Refund getRefund() {
        return refund;
    }

    public int getRefundedID() {
        return refundedID;
    }
}
