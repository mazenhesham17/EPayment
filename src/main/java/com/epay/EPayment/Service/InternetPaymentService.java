package com.epay.EPayment.Service;

import com.epay.EPayment.Controller.FormController;
import com.epay.EPayment.Controller.PaymentController;
import com.epay.EPayment.Controller.ServiceController;
import com.epay.EPayment.Models.Service;

public class InternetPaymentService extends Service {
    public InternetPaymentService() {
        super.setCategoryName("Internet Payment");
        setKey("Internet bundle");
        FormController formController = FormController.getInstance();
        formController.setForm(super.getForm());
        formController.addField("Mobile number", new String[]{});
        formController.addField("Internet bundle", new String[]{
                "120", "170", "340", "500"
        });
        ServiceController serviceController = ServiceController.getInstance();
        serviceController.setService(this);
        serviceController.addCompany("Etisalat");
        serviceController.addCompany("Vodafone");
        serviceController.addCompany("Orange");
        serviceController.addCompany("We");
        PaymentController paymentController = PaymentController.getInstance();
        serviceController.addPaymentMethod(paymentController.getPayment(1));
        serviceController.addPaymentMethod(paymentController.getPayment(2));
    }

    @Override
    public Service clone(int index) {
        InternetPaymentService internetPaymentService = new InternetPaymentService();
        ServiceController serviceController = ServiceController.getInstance();
        serviceController.setService(internetPaymentService);
        serviceController.chooseCompany(index);
        return internetPaymentService;
    }
}
