package com.epay.EPayment.Controller;

import com.epay.EPayment.DataSet.ServiceData;
import com.epay.EPayment.Discount.OverallDiscount;
import com.epay.EPayment.Discount.SpecificDiscount;
import com.epay.EPayment.Models.Discount;
import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.Models.Service;
import com.epay.EPayment.View.ServiceView;

import java.util.HashMap;
import java.util.Vector;

public class ServiceController {

    static ServiceController serviceController = null;
    Service service;

    private ServiceController() {
    }

    public static ServiceController getInstance() {
        if (serviceController == null)
            serviceController = new ServiceController();
        return serviceController;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void addService(Service service) {
        ServiceData serviceData = ServiceData.getInstance();
        serviceData.getServices().add(service);
    }

    public HashMap<String, String[]> getFormFields() {
        FormController formController = FormController.getInstance();
        formController.setForm(service.getForm());
        return formController.getFields();
    }

    public void setFormDataField(String key, String value) {
        FormDataController formDataController = FormDataController.getInstance();
        formDataController.setFormData(service.getFormData());
        formDataController.setData(key, value);
    }

    public void addPaymentMethod(Payment payment) {
        service.getPayments().add(payment);
    }

    public void addCompany(String name) {
        service.getCompanies().add(name);
    }

    public void chooseCompany(int index) {
        service.setCurrentCompany(service.getCompanies().get(index - 1));
    }

    public void choosePayment(int index) {
        service.setCurrentPayment(service.getPayments().get(index - 1).clone(0));
    }

    public void showPayments() {
        PaymentController paymentController = PaymentController.getInstance();
        paymentController.showPayments(service.getPayments());
    }

    public Payment getCurrentPayment() {
        return service.getCurrentPayment();
    }

    public void setPassword(String password) {
        PaymentController paymentController = PaymentController.getInstance();
        paymentController.setPayment(service.getCurrentPayment());
        paymentController.setPassword(password);
    }

    public void showServices(Vector<Service> services) {
        ServiceView serviceView = ServiceView.getInstance();
        serviceView.showServices(services);
    }

    public Service chooseService(Vector<Service> services, int index) {
        return services.get(index - 1);
    }

    public double getCost() {
        return service.getCost();
    }

    public void setCost(double cost) {
        PaymentController paymentController = PaymentController.getInstance();
        paymentController.setPayment(service.getCurrentPayment());
        paymentController.setCost(cost);
    }

    public Service chooseCategory(int index) {
        ServiceData serviceData = ServiceData.getInstance();
        return serviceData.getServices().get(index - 1);
    }

    public void showCategories() {
        ServiceView serviceView = ServiceView.getInstance();
        serviceView.showCategories();
    }

    public void setDiscountData() {
        CustomerController customerController = CustomerController.getInstance();
        DiscountController discountController = DiscountController.getInstance();
        discountController.setDiscountData(customerController.getDiscountData());
    }

    public Vector<Discount> useDiscounts() {
        DiscountController discountController = DiscountController.getInstance();
        return discountController.useDiscounts(service);
    }

    public double applyDiscounts(double before, Vector<Discount> discounts) {
        double percentage = 100;
        for (Discount discount : discounts) {
            percentage -= discount.getPercentage();
        }
        percentage /= 100;
        return before * percentage;
    }

    public void returnDiscounts(Vector<Discount> discounts) {
        DiscountController discountController = DiscountController.getInstance();
        for (Discount discount : discounts) {
            if (discount instanceof OverallDiscount) {
                discountController.addOverallDiscount(discount);
            } else {
                SpecificDiscount specificDiscount = (SpecificDiscount) discount;
                discountController.addSpecificDiscount(specificDiscount, specificDiscount.getCategory());
            }
        }
    }

    public void showDiscounts(Vector<Discount> discounts) {
        DiscountController discountController = DiscountController.getInstance();
        discountController.showList(discounts);
    }

    public void pay() throws Exception {
        service.getCurrentPayment().pay();
    }


}
