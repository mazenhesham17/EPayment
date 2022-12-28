package com.epay.EPayment.Models;

import com.epay.EPayment.Controller.FormDataController;

import java.util.Vector;

public abstract class Service {
    protected Vector<Payment> payments;
    protected Payment currentPayment;
    protected Form form;

    protected String key;
    protected FormData formData;
    protected String categoryName;
    protected String currentCompany;
    protected Vector<String> companies;

    public Service() {
        payments = new Vector<>();
        companies = new Vector<>();
        form = new Form();
        formData = new FormData();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCurrentCompany(String currentCompany) {
        this.currentCompany = currentCompany;
    }

    public Form getForm() {
        return form;
    }

    public Payment getCurrentPayment() {
        return currentPayment;
    }

    public void setCurrentPayment(Payment currentPayment) {
        this.currentPayment = currentPayment;
    }

    public Vector<Payment> getPayments() {
        return payments;
    }

    public Vector<String> getCompanies() {
        return companies;
    }

    public FormData getFormData() {
        return formData;
    }

    public String getName() {
        return currentCompany + " " + categoryName;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getCost() {
        FormDataController formDataController = FormDataController.getInstance();
        formDataController.setFormData(formData);
        String cost = formDataController.getData(key);
        return Double.parseDouble(cost);
    }

    public abstract Service clone(int index);

}
