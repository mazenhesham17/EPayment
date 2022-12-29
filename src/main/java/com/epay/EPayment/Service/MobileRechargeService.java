package com.epay.EPayment.Service;

import com.epay.EPayment.Controller.FormController;
import com.epay.EPayment.Controller.PaymentController;
import com.epay.EPayment.Controller.ServiceController;
import com.epay.EPayment.Models.Service;

public class MobileRechargeService extends Service {
    public MobileRechargeService(MobileRechargeService mobileRechargeService){
        super.setCategoryName(mobileRechargeService.getCategoryName());
        setKey(mobileRechargeService.getKey());
        formInitializer();
        setCompanyName(mobileRechargeService.getCompanyName());
        paymentInitializer();
    }
    @Override
    protected void formInitializer() {
        FormController formController = FormController.getInstance();
        formController.setForm(super.getForm());
        formController.addField("Mobile number", new String[]{});
        formController.addField("Amount", new String[]{});
    }
    @Override
    protected void paymentInitializer() {
        ServiceController serviceController = ServiceController.getInstance();
        PaymentController paymentController = PaymentController.getInstance();
        serviceController.addPaymentMethod(paymentController.getPayment(1));
        serviceController.addPaymentMethod(paymentController.getPayment(2));
    }

    public MobileRechargeService() {
        super.setCategoryName("Mobile Recharge");
        setKey("Amount");
        formInitializer();
        ServiceController serviceController = ServiceController.getInstance();
        serviceController.setService(this);
        serviceController.addCompany("Etisalat");
        serviceController.addCompany("Vodafone");
        serviceController.addCompany("Orange");
        serviceController.addCompany("We");
       paymentInitializer();
    }


}
