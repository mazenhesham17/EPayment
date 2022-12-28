package com.epay.EPayment.Service;

import com.epay.EPayment.Controller.FormController;
import com.epay.EPayment.Controller.PaymentController;
import com.epay.EPayment.Controller.ServiceController;
import com.epay.EPayment.Models.Service;

public class MobileRechargeService extends Service {

    public MobileRechargeService() {
        super.setCategoryName("Mobile Recharge");
        setKey("Amount");
        FormController formController = FormController.getInstance();
        formController.setForm(super.getForm());
        formController.addField("Mobile number", new String[]{});
        formController.addField("Amount", new String[]{});
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
        MobileRechargeService mobileRechargeService = new MobileRechargeService();
        ServiceController serviceController = ServiceController.getInstance();
        serviceController.setService(mobileRechargeService);
        serviceController.chooseCompany(index);
        return mobileRechargeService;
    }

}
