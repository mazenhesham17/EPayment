package com.epay.EPayment.View;

import com.epay.EPayment.Controller.ContainerController;
import com.epay.EPayment.Util.Container;

public class TransactionWebView {
    static TransactionWebView transactionView = null;

    private TransactionWebView() {
    }

    public static TransactionWebView getInstance() {
        if (transactionView == null)
            transactionView = new TransactionWebView();
        return transactionView;
    }

    public Container showCommonTransaction(String username, String time, int id, String type, String paymentMethod) {
        Container container = new Container();
        ContainerController containerController = ContainerController.getInstance();
        containerController.setContainer(container);
        containerController.put("username", username);
        containerController.put("transactionTime", time);
        containerController.put("transactionId", id);
        containerController.put("transactionType", type);
        containerController.put("paymentMethod", paymentMethod);
        return container;
    }
}
