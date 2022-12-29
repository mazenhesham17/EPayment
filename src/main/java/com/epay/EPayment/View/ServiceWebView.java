package com.epay.EPayment.View;

import com.epay.EPayment.Controller.ContainerController;
import com.epay.EPayment.Models.Form;
import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.Models.Service;
import com.epay.EPayment.Util.Container;

import java.util.Vector;

public class ServiceWebView {
    static ServiceWebView serviceWebView = null;
    Service service;

    private ServiceWebView() {
    }

    public static ServiceWebView getInstance() {
        if (serviceWebView == null)
            serviceWebView = new ServiceWebView();
        return serviceWebView;
    }

    public void setService(Service service) {
        this.service = service;
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
