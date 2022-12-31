package com.epay.EPayment.Service;

import com.epay.EPayment.Controller.FormController;
import com.epay.EPayment.Payment.Payment;

import java.util.Vector;

public class InternetPaymentService extends Service {

    public InternetPaymentService(String categoryName, String key, Vector<Payment> payments) {
        super(categoryName, key, payments);
    }

    public InternetPaymentService(InternetPaymentService internetPaymentService) {
        super(internetPaymentService.getCategoryName(), internetPaymentService.getKey(), internetPaymentService.getPayments());
        setCompanyName(internetPaymentService.getCompanyName());
        formInitializer();

    }

    @Override
    protected void formInitializer() {
        FormController formController = FormController.getInstance();
        formController.setForm(super.getForm());
        formController.addField("Mobile number", new String[]{});
        formController.addField("Internet bundle", new String[]{
                "120", "170", "340", "500"
        });
    }

    @Override
    public Service clone(int dummy) {
        return new InternetPaymentService(this);
    }

}
