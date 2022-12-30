package com.epay.EPayment.View;

import com.epay.EPayment.Controller.ContainerController;
import com.epay.EPayment.Util.Container;

public class DiscountWebView {
    static DiscountWebView discountView = null;

    private DiscountWebView() {

    }

    public static DiscountWebView getInstance() {
        if (discountView == null)
            discountView = new DiscountWebView();
        return discountView;
    }

    public Container showDiscount(String name, int percentage, String categoryName) {
        Container container = new Container();
        ContainerController containerController = ContainerController.getInstance();
        containerController.setContainer(container);
        containerController.put("name", name);
        containerController.put("percentage", percentage);
        containerController.put("categoryName", categoryName);
        return container;
    }
}
