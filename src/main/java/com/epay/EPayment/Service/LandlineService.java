package com.epay.EPayment.Service;

import com.epay.EPayment.Controller.FormController;
import com.epay.EPayment.Controller.PaymentController;
import com.epay.EPayment.Controller.ServiceController;
import com.epay.EPayment.Models.Service;

public class LandlineService extends Service {
    public LandlineService(LandlineService landlineService){
        super.setCategoryName(landlineService.getCategoryName());
        setKey(landlineService.getKey());
        formInitializer();
        setCompanyName(landlineService.getCompanyName());
        paymentInitializer();
    }
    @Override
    protected void formInitializer() {
        FormController formController = FormController.getInstance();
        formController.setForm(super.getForm());
        formController.addField("Number", new String[]{});
    }

    @Override
    protected void paymentInitializer() {
        PaymentController paymentController = PaymentController.getInstance();
        ServiceController serviceController = ServiceController.getInstance();
        serviceController.addPaymentMethod(paymentController.getPayment(1));
        serviceController.addPaymentMethod(paymentController.getPayment(2));
    }

    public LandlineService() {
        super.setCategoryName("Landline");
        setKey("Landline Bill");
       formInitializer();
        ServiceController serviceController = ServiceController.getInstance();
        serviceController.setService(this);
        serviceController.addCompany("Quarter Bill");
        serviceController.addCompany("Monthly Bill");
        paymentInitializer();
    }

}
