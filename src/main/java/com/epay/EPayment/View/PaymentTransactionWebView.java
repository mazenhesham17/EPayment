package com.epay.EPayment.View;

import com.epay.EPayment.Controller.ContainerController;
import com.epay.EPayment.Models.FormData;
import com.epay.EPayment.Util.Container;

public class PaymentTransactionWebView {
    static PaymentTransactionWebView paymentTransactionWebView = null;

    private PaymentTransactionWebView() {
    }

    public static PaymentTransactionWebView getInstance() {
        if (paymentTransactionWebView == null)
            paymentTransactionWebView = new PaymentTransactionWebView();
        return paymentTransactionWebView;
    }

    public Container showPaymentTransaction(String username, String time, int id, String type, String paymentMethod, String serviceName, FormData formData, double before, double after) {
        ContainerController containerController = ContainerController.getInstance();
        TransactionWebView transactionWebView = TransactionWebView.getInstance();
        Container container = (transactionWebView.showCommonTransaction(username, time, id, type, paymentMethod));
        containerController.setContainer(container);
        containerController.put("beforeDiscount", before);
        containerController.put("afterDiscount", after);
        containerController.put("form", formData);
        containerController.put("serviceName", serviceName);
        return container;
    }
}
