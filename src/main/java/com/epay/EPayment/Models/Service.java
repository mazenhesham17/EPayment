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
    protected String companyName;
    protected int id;

    public Service(String categoryName, String key, Vector<Payment> payments) {
        this.categoryName = categoryName;
        this.key = key;
        this.payments = payments;
        form = new Form();
        formData = new FormData();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public FormData getFormData() {
        return formData;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    protected abstract void formInitializer();
}
