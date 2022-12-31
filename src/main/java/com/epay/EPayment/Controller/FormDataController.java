package com.epay.EPayment.Controller;

import com.epay.EPayment.Models.FormData;

public class FormDataController {
    static FormDataController formDataController = null;
    FormData formData;

    private FormDataController() {
    }

    public static FormDataController getInstance() {
        if (formDataController == null)
            formDataController = new FormDataController();
        return formDataController;
    }

    public void setFormData(FormData formData) {
        this.formData = formData;
    }

    public void setData(String key, String value) {
        formData.getFields().put(key, value);
    }

    public String getData(String key) {
        return formData.getFields().get(key);
    }
}
