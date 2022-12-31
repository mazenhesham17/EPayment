package com.epay.EPayment.Transaction;

import com.epay.EPayment.Controller.PaymentController;
import com.epay.EPayment.Controller.ServiceController;
import com.epay.EPayment.Models.Customer;
import com.epay.EPayment.Models.FormData;
import com.epay.EPayment.Service.Service;

public class PaymentTransaction extends Transaction {
    String serviceName;
    double beforeDiscount;
    FormData formData;

    public PaymentTransaction(Customer customer, Service service, double before, double amount) {
        super(customer, amount);
        super.setTransactionType("Payment Transaction");
        ServiceController serviceController = ServiceController.getInstance();
        PaymentController paymentController = PaymentController.getInstance();
        serviceController.setService(service);
        super.setPaymentMethod(paymentController.getName());
        serviceName = serviceController.getName();
        this.formData = serviceController.getFormData();
        this.beforeDiscount = before;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getBeforeDiscount() {
        return beforeDiscount;
    }

    public FormData getFormData() {
        return formData;
    }
}
