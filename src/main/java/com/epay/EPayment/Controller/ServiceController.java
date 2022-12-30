package com.epay.EPayment.Controller;

import com.epay.EPayment.DataSet.ServiceData;
import com.epay.EPayment.Discount.OverallDiscount;
import com.epay.EPayment.Discount.SpecificDiscount;
import com.epay.EPayment.Models.Discount;
import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.Models.Service;
import com.epay.EPayment.Service.DonationsService;
import com.epay.EPayment.Service.InternetPaymentService;
import com.epay.EPayment.Service.LandlineService;
import com.epay.EPayment.Service.MobileRechargeService;
import com.epay.EPayment.Util.Container;
import com.epay.EPayment.Util.Pair;
import com.epay.EPayment.View.ServiceWebView;

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

    public Service chooseService(Vector<Service> services, int index) {
        return services.get(index - 1);
    }

    public double getCost() {
        FormDataController formDataController = FormDataController.getInstance();
        formDataController.setFormData(service.getFormData());
        String cost = formDataController.getData(service.getKey());
        if (cost == null)
            return 0;
        return Double.parseDouble(cost);
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

    public void setDiscountData() {
        CustomerController customerController = CustomerController.getInstance();
        DiscountController discountController = DiscountController.getInstance();
        discountController.setDiscountData(customerController.getDiscountData());
    }

    public String getName() {
        return service.getCompanyName() + " " + service.getCategoryName();
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

    public Vector<Container> getWebView(Vector<Service> services) {
        Vector<Container> containers = new Vector<>();
        ServiceWebView serviceWebView = ServiceWebView.getInstance();
        for (Service concreteService : services) {
            service = concreteService;
            containers.add(serviceWebView.showService(serviceController.getName(), concreteService.getId(), concreteService.getForm(), concreteService.getPayments()));
        }
        return containers;
    }

    public Vector<Container> getServices() {
        ServiceData serviceData = ServiceData.getInstance();
        Vector<Service> services = serviceData.getServices();
        return getWebView(services);
    }

    public void pay() throws Exception {
        service.getCurrentPayment().pay();
    }

    public void addCategory(Service service, Vector<String> companies) {
        ServiceData serviceData = ServiceData.getInstance();
        int id = serviceData.getCategories().size() + 1;
        serviceData.getCategories().put(service, new Pair(id, companies));
        Vector<Service> services = serviceData.getServices();
        for (String company : companies) {
            id = services.size() + 1;
            if (service instanceof DonationsService) {
                DonationsService donationsService = new DonationsService((DonationsService) service);
                donationsService.setCompanyName(company);
                donationsService.setId(id);
                services.add(donationsService);
            } else if (service instanceof MobileRechargeService) {
                MobileRechargeService mobileRechargeService = new MobileRechargeService((MobileRechargeService) service);
                mobileRechargeService.setCompanyName(company);
                mobileRechargeService.setId(id);
                services.add(mobileRechargeService);
            } else if (service instanceof LandlineService) {
                LandlineService landlineService = new LandlineService((LandlineService) service);
                landlineService.setCompanyName(company);
                landlineService.setId(id);
                services.add(landlineService);
            } else if (service instanceof InternetPaymentService) {
                InternetPaymentService internetPaymentService = new InternetPaymentService((InternetPaymentService) service);
                internetPaymentService.setCompanyName(company);
                internetPaymentService.setId(id);
                services.add(internetPaymentService);
            }
        }
    }


}