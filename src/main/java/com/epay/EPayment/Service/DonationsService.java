package com.epay.EPayment.Service;

import com.epay.EPayment.Controller.FormController;
import com.epay.EPayment.Controller.PaymentController;
import com.epay.EPayment.Controller.ServiceController;
import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.Models.Service;

import java.util.Vector;

public class DonationsService extends Service {
    public DonationsService(String categoryName, String key, Vector<Payment> payments) {
        super(categoryName, key, payments);
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
    @Override
    protected void paymentInitializer() {
        PaymentController paymentController = PaymentController.getInstance();
        ServiceController serviceController = ServiceController.getInstance();
        serviceController.setService(this);
        serviceController.addPaymentMethod(paymentController.getPayment(1));
        serviceController.addPaymentMethod(paymentController.getPayment(2));
        serviceController.addPaymentMethod(paymentController.getPayment(3));
    }
//    public DonationsService(DonationsService donationsService){
//        super();
//        super.setCategoryName(donationsService.getCategoryName());
//        setKey(donationsService.getKey());
//        formInitializer();
//        setCompanyName(donationsService.getCompanyName());
//        paymentInitializer();
//    }
//    public DonationsService() {
//        super.setCategoryName("Donation");
//        setKey("Monthly Donation");
//        ServiceController serviceController = ServiceController.getInstance();
//        serviceController.setService(this);
//        serviceController.addCompany("Cancer Hospital");
//        serviceController.addCompany("Schools");
//        serviceController.addCompany("NGOs( Non Profitable Organizations)");
//
//    }

}
