package com.epay.EPayment.WebView;

import com.epay.EPayment.Controller.ContainerController;
import com.epay.EPayment.Util.Container;

public class CreditCardWebView {

    static CreditCardWebView creditCardView = null;

    private CreditCardWebView() {
    }

    public static CreditCardWebView getInstance() {
        if (creditCardView == null)
            creditCardView = new CreditCardWebView();
        return creditCardView;
    }

    public Container showCard(String name, double amount, int id) {
        Container container = new Container();
        ContainerController containerController = ContainerController.getInstance();
        containerController.setContainer(container);
        containerController.put("name", name);
        containerController.put("amount", amount);
        containerController.put("id", id);
        return container;
    }
}
