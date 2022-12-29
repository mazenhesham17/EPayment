package com.epay.EPayment.Service;

import com.epay.EPayment.Controller.FormController;
import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.Models.Service;

import java.util.Vector;

public class LandlineService extends Service {
    public LandlineService(String categoryName, String key, Vector<Payment> payments) {
        super(categoryName, key, payments);
    }

    public LandlineService(LandlineService landlineService) {
        super(landlineService.getCategoryName(), landlineService.getKey(), landlineService.getPayments());
        formInitializer();
    }

    @Override
    protected void formInitializer() {
        FormController formController = FormController.getInstance();
        formController.setForm(super.getForm());
        formController.addField("Number", new String[]{});
    }


}
