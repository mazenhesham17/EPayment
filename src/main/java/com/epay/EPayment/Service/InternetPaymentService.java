package com.epay.EPayment.Service;

import com.epay.EPayment.Controller.FormController;
import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.Models.Service;

import java.util.Vector;

public class InternetPaymentService extends Service {

    public InternetPaymentService(String categoryName, String key, Vector<Payment> payments) {
        super(categoryName, key, payments);
    }

    public InternetPaymentService(InternetPaymentService internetPaymentService) {
        super(internetPaymentService.getCategoryName(), internetPaymentService.getKey(), internetPaymentService.getPayments());
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

}
