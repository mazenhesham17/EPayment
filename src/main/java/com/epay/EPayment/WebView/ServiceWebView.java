package com.epay.EPayment.WebView;

import com.epay.EPayment.Controller.ContainerController;
import com.epay.EPayment.Models.Form;
import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.Util.Container;

import java.util.Vector;

public class ServiceWebView {
    static ServiceWebView serviceWebView = null;

    private ServiceWebView() {
    }

    public static ServiceWebView getInstance() {
        if (serviceWebView == null)
            serviceWebView = new ServiceWebView();
        return serviceWebView;
    }


    public Container showService(String name, int id, Form form, Vector<Payment> payments) {
        Container container = new Container();
        ContainerController containerController = ContainerController.getInstance();
        containerController.setContainer(container);
        containerController.put("name", name);
        containerController.put("id", id);
        containerController.put("form", form);
        containerController.put("supported payments", payments);
        return container;
    }

}
