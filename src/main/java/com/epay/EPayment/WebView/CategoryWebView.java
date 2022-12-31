package com.epay.EPayment.WebView;

import com.epay.EPayment.Controller.ContainerController;
import com.epay.EPayment.Util.Container;

public class CategoryWebView {
    static CategoryWebView categoryWebView = null;

    private CategoryWebView() {
    }

    public static CategoryWebView getInstance() {
        if (categoryWebView == null)
            categoryWebView = new CategoryWebView();
        return categoryWebView;
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