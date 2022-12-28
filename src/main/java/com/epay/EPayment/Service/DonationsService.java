package com.epay.EPayment.Service;

import com.epay.EPayment.Controller.FormController;
import com.epay.EPayment.Controller.PaymentController;
import com.epay.EPayment.Controller.ServiceController;
import com.epay.EPayment.Models.Service;

public class DonationsService extends Service {
    public DonationsService() {
        super.setCategoryName("Donation");
        setKey("Monthly Donation");
        FormController formController = FormController.getInstance();
        formController.setForm(super.getForm());
        formController.addField("Mobile number", new String[]{});
        formController.addField("Address", new String[]{});
        formController.addField("Monthly Donation", new String[]{
                "100", "250", "500", "10000"
        });
        ServiceController serviceController = ServiceController.getInstance();
        serviceController.setService(this);
        serviceController.addCompany("Cancer Hospital");
        serviceController.addCompany("Schools");
        serviceController.addCompany("NGOs( Non Profitable Organizations)");
        PaymentController paymentController = PaymentController.getInstance();
        serviceController.addPaymentMethod(paymentController.getPayment(1));
        serviceController.addPaymentMethod(paymentController.getPayment(2));
        serviceController.addPaymentMethod(paymentController.getPayment(3));
    }

    @Override
    public Service clone(int index) {
        DonationsService donationsService = new DonationsService();
        ServiceController serviceController = ServiceController.getInstance();
        serviceController.setService(donationsService);
        serviceController.chooseCompany(index);
        return donationsService;
    }

}
