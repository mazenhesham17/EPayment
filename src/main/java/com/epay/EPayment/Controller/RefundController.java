package com.epay.EPayment.Controller;

import com.epay.EPayment.Models.Customer;
import com.epay.EPayment.Models.Refund;
import com.epay.EPayment.Models.RefundState;
import com.epay.EPayment.Transaction.Transaction;
import com.epay.EPayment.Util.Container;
import com.epay.EPayment.WebView.RefundWebView;

import java.util.Vector;

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

    public int getId() {
        return refund.getId();
    }

    public Vector<Container> getRefunds(Vector<Refund> refunds) {
        Vector<Container> containers = new Vector<>();
        RefundWebView refundWebView = RefundWebView.getInstance();
        for (Refund concreteRefund : refunds) {
            CustomerController customerController = CustomerController.getInstance();
            customerController.setCustomer(concreteRefund.getCustomer());
            TransactionController transactionController = TransactionController.getInstance();
            transactionController.setTransaction(concreteRefund.getTransaction());
            containers.add(refundWebView.showRefundDetails(customerController.getUserName(), concreteRefund.getId(),
                    transactionController.getID(), concreteRefund.getRefundState(), transactionController.getAmount()));
        }
        return containers;
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

    public boolean isPending() {
        return refund.getRefundState() == RefundState.PENDING;
    }

    public Transaction getTransaction() {
        return refund.getTransaction();
    }
}
