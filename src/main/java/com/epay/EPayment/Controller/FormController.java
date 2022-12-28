package com.epay.EPayment.Controller;

import com.epay.EPayment.Models.Form;

import java.util.HashMap;

public class FormController {
    static FormController formController = null;
    Form form;

    private FormController() {
    }

    public static FormController getInstance() {
        if (formController == null)
            formController = new FormController();
        return formController;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public HashMap<String, String[]> getFields() {
        return form.getFields();
    }

    public void addField(String key, String[] items) {
        form.getFields().put(key, items);
    }

}
