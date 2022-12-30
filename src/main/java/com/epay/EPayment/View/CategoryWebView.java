package com.epay.EPayment.View;

import com.epay.EPayment.Controller.ContainerController;
import com.epay.EPayment.Models.Form;
import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.Models.Service;
import com.epay.EPayment.Util.Container;

import java.util.Vector;

public class CategoryWebView {
    static CategoryWebView categoryWebView = null;

    private CategoryWebView() {
    }

    public static CategoryWebView getInstance() {
        if (categoryWebView == null)
            categoryWebView = new CategoryWebView();
        return categoryWebView ;
    }
    public Container showCategory(String name, int id) {
        Container container = new Container();
        ContainerController containerController = ContainerController.getInstance();
        containerController.setContainer(container);
        containerController.put("name", name);
        containerController.put("id", id);
        return container;
    }
}