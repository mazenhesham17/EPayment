package com.epay.EPayment.WebView;

import com.epay.EPayment.Controller.ContainerController;
import com.epay.EPayment.Models.RefundState;
import com.epay.EPayment.Util.Container;

public class RefundWebView {
    static RefundWebView refundWebView = null;

    private RefundWebView() {

    }

    public static RefundWebView getInstance() {
        if (refundWebView == null)
            refundWebView = new RefundWebView();
        return refundWebView;
    }

    public Container showRefundDetails(String username, int id, int transactionId, RefundState refundState, double amount) {
        Container container = new Container();
        ContainerController containerController = ContainerController.getInstance();
        containerController.setContainer(container);
        containerController.put("username", username);
        containerController.put("refundId", id);
        containerController.put("transactionId", transactionId);
        containerController.put("refundState", refundState);
        containerController.put("amount", amount);
        return container;
    }
}
