package com.epay.EPayment.WebView;

import com.epay.EPayment.Controller.ContainerController;
import com.epay.EPayment.Util.Container;

public class ChargeTransactionWebView {
    static ChargeTransactionWebView chargeTransactionWebView = null;

    private ChargeTransactionWebView() {
    }

    public static ChargeTransactionWebView getInstance() {
        if (chargeTransactionWebView == null)
            chargeTransactionWebView = new ChargeTransactionWebView();
        return chargeTransactionWebView;
    }

    public Container showChargeTransaction(String username, String time, int id, String type, String paymentMethod, String cardName, double amount) {
        ContainerController containerController = ContainerController.getInstance();
        TransactionWebView transactionWebView = TransactionWebView.getInstance();
        Container container = (transactionWebView.showCommonTransaction(username, time, id, type, paymentMethod));
        containerController.setContainer(container);
        containerController.put("cardName", cardName);
        containerController.put("amountCharged", amount);
        return container;
    }
}
