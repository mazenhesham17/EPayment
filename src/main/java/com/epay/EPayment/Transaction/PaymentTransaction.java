package com.epay.EPayment.Transaction;

import com.epay.EPayment.Controller.ServiceController;
import com.epay.EPayment.Models.Customer;
import com.epay.EPayment.Models.FormData;
import com.epay.EPayment.Models.Service;
import com.epay.EPayment.Models.Transaction;

public class PaymentTransaction extends Transaction {
    String serviceName;
    double beforeDiscount;
    FormData formData;

    public PaymentTransaction(Customer customer, Service service, double before, double amount) {
        super(customer, amount);
        super.setTransactionType("Payment Transaction");
        super.setPaymentMethod(service.getCurrentPayment().getName());
        ServiceController serviceController = ServiceController.getInstance() ;
        serviceController.setService(service);
        serviceName = serviceController.getName();
        this.formData = service.getFormData();
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
