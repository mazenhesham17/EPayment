package com.epay.EPayment.Controller;

import com.epay.EPayment.DataSet.ServiceData;
import com.epay.EPayment.Models.FormData;
import com.epay.EPayment.Payment.Payment;
import com.epay.EPayment.Service.Service;
import com.epay.EPayment.Util.Container;
import com.epay.EPayment.WebView.ServiceWebView;

import java.util.HashMap;
import java.util.Vector;

public class ServiceController {

    static ServiceController serviceController = null;
    Service service;

    private ServiceController() {
    }

    public static ServiceController getInstance() {
        if (serviceController == null)
            serviceController = new ServiceController();
        return serviceController;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void addService(Service service) {
        ServiceData serviceData = ServiceData.getInstance();
        serviceData.getServices().add(service);
    }

    public HashMap<String, String[]> getFormFields() {
        FormController formController = FormController.getInstance();
        formController.setForm(service.getForm());
        return formController.getFields();
    }


    public FormData getFormData() {
        return service.getFormData();
    }

    public void addPaymentMethod(Payment payment) {
        service.getPayments().add(payment);
    }

    public Payment choosePayment(int id) throws Exception {
        if (id < 1 || id > service.getPayments().size()) {
            throw new Exception("Payment id is not in range from 1 to " + service.getPayments().size());
        }
        Payment payment = service.getPayments().get(id - 1).clone(0);
        service.setCurrentPayment(payment);
        return payment;
    }

    public Payment getCurrentPayment() {
        return service.getCurrentPayment();
    }

    public void setPassword(String password) {
        PaymentController paymentController = PaymentController.getInstance();
        paymentController.setPayment(service.getCurrentPayment());
        paymentController.setPassword(password);
    }

    public Service chooseService(Vector<Service> services, int index) {
        return services.get(index - 1);
    }

    public double getCost() {
        FormDataController formDataController = FormDataController.getInstance();
        formDataController.setFormData(service.getFormData());
        String cost = formDataController.getData(service.getKey());
        if (cost == null)
            return 0;
        return Double.parseDouble(cost);
    }

    public Service chooseCategory(int index) {
        ServiceData serviceData = ServiceData.getInstance();
        return serviceData.getServices().get(index - 1);
    }

    public String getName() {
        return service.getCompanyName() + " " + service.getCategoryName();
    }

    public Vector<Payment> getPayments() {
        return service.getPayments();
    }


    public Vector<Container> getWebView(Vector<Service> services) {
        Vector<Container> containers = new Vector<>();
        ServiceWebView serviceWebView = ServiceWebView.getInstance();
        for (Service concreteService : services) {
            service = concreteService;
            containers.add(serviceWebView.showService(serviceController.getName(), concreteService.getId(), concreteService.getForm(), concreteService.getPayments()));
        }
        return containers;
    }

    public Container getService(int id) throws Exception {
        ServiceData serviceData = ServiceData.getInstance();
        ServiceWebView serviceWebView = ServiceWebView.getInstance();
        if (id < 1 || id > serviceData.getServices().size())
            throw new Exception("Service id not in the range from 1 to " + serviceData.getServices().size());
        Service concreteService = serviceData.getServices().get(id - 1);
        service = concreteService;
        return serviceWebView.showService(serviceController.getName(), concreteService.getId(), concreteService.getForm(), concreteService.getPayments());
    }

    public String getCurrentPaymentName() {
        PaymentController paymentController = PaymentController.getInstance();
        paymentController.setPayment(service.getCurrentPayment());
        return paymentController.getName();
    }


    public Service chooseService(int id) throws Exception {
        ServiceData serviceData = ServiceData.getInstance();
        if (id < 1 || id > serviceData.getServices().size())
            throw new Exception("Service id not in the range from 1 to " + serviceData.getServices().size());
        return serviceData.getServices().get(id - 1).clone(0);
    }

    public Vector<Container> getServices() {
        ServiceData serviceData = ServiceData.getInstance();
        Vector<Service> services = serviceData.getServices();
        return getWebView(services);
    }
}