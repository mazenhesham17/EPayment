package com.epay.EPayment.View;

import com.epay.EPayment.Controller.ContainerController;
import com.epay.EPayment.Util.Container;

public class UserWebView {
    static UserWebView userWebView = null;

    private UserWebView() {

    }

    public static UserWebView getInstance() {
        if (userWebView == null)
            userWebView = new UserWebView();
        return userWebView;
    }

    public Container showUser(String username, String email) {
        Container container = new Container();
        ContainerController containerController = ContainerController.getInstance();
        containerController.setContainer(container);
        containerController.put("username", username);
        containerController.put("email", email);
        return container;
    }
}
