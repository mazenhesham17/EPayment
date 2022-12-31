package com.epay.EPayment.Service;

import com.epay.EPayment.Controller.FormController;
import com.epay.EPayment.Payment.Payment;

import java.util.Vector;

public class MobileRechargeService extends Service {
    public MobileRechargeService(String categoryName, String key, Vector<Payment> payments) {
        super(categoryName, key, payments);
    }

    public MobileRechargeService(MobileRechargeService mobileRechargeService) {
        super(mobileRechargeService.getCategoryName(), mobileRechargeService.getKey(), mobileRechargeService.getPayments());
        setCompanyName(mobileRechargeService.getCompanyName());
        formInitializer();
    }

    @Override
    protected void formInitializer() {
        FormController formController = FormController.getInstance();
        formController.setForm(super.getForm());
        formController.addField("Mobile number", new String[]{});
        formController.addField("Amount", new String[]{});
    }

    @Override
    public Service clone(int dummy) {
        return new MobileRechargeService(this);
    }


}
