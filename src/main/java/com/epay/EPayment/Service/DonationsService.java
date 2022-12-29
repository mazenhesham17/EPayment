package com.epay.EPayment.Service;

import com.epay.EPayment.Controller.FormController;
import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.Models.Service;

import java.util.Vector;

public class DonationsService extends Service {
    public DonationsService(String categoryName, String key, Vector<Payment> payments) {
        super(categoryName, key, payments);
    }

    public DonationsService(DonationsService donationsService) {
        super(donationsService.getCategoryName(), donationsService.getKey(), donationsService.getPayments());
        formInitializer();
    }

    @Override
    protected void formInitializer() {
        FormController formController = FormController.getInstance();
        formController.setForm(super.getForm());
        formController.addField("Mobile number", new String[]{});
        formController.addField("Address", new String[]{});
        formController.addField("Monthly Donation", new String[]{
                "100", "250", "500", "10000"
        });
    }

}
