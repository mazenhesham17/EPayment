package com.epay.EPayment.Service;

import com.epay.EPayment.Controller.FormController;
import com.epay.EPayment.Controller.FormDataController;
import com.epay.EPayment.Controller.PaymentController;
import com.epay.EPayment.Controller.ServiceController;
import com.epay.EPayment.Models.Service;

public class LandlineService extends Service {
    public LandlineService() {
        super.setCategoryName("Landline");
        setKey("Landline Bill");
        FormController formController = FormController.getInstance();
        formController.setForm(super.getForm());
        formController.addField("Number", new String[]{});
        ServiceController serviceController = ServiceController.getInstance();
        serviceController.setService(this);
        serviceController.addCompany("Quarter Bill");
        serviceController.addCompany("Monthly Bill");
        PaymentController paymentController = PaymentController.getInstance();
        serviceController.addPaymentMethod(paymentController.getPayment(1));
        serviceController.addPaymentMethod(paymentController.getPayment(2));
    }

    @Override
    public Service clone(int index) {
        LandlineService landlineService = new LandlineService();
        ServiceController serviceController = ServiceController.getInstance();
        serviceController.setService(landlineService);
        serviceController.chooseCompany(index);
        FormDataController formDataController = FormDataController.getInstance();
        formDataController.setFormData(landlineService.getFormData());
        if (index == 1) {
            formDataController.setData("Landline Bill", Integer.toString(3 * 50));
        } else {
            formDataController.setData("Landline Bill", Integer.toString(50));
        }
        return landlineService;
    }
}
