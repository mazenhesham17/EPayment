package com.epay.EPayment.View;

import com.epay.EPayment.Controller.ContainerController;
import com.epay.EPayment.Util.Container;

public class RefundTransactionWebView {
    static RefundTransactionWebView refundTransactionWebView = null;

    private RefundTransactionWebView() {
    }

    public static RefundTransactionWebView getInstance() {
        if (refundTransactionWebView == null)
            refundTransactionWebView = new RefundTransactionWebView();
        return refundTransactionWebView;
    }

    public Container showRefundTransaction(String username, String time, int id, String type, String paymentMethod, double amount, int prevId) {
        ContainerController containerController = ContainerController.getInstance();
        TransactionWebView transactionWebView = TransactionWebView.getInstance();
        Container container = (transactionWebView.showCommonTransaction(username, time, id, type, paymentMethod));
        containerController.setContainer(container);
        containerController.put("amountRefunded", amount);
        containerController.put("refundedTransactionId", prevId);
        return container;
    }
}
