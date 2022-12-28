package com.epay.EPayment.View;

import com.epay.EPayment.Models.FormData;

import java.util.HashMap;
import java.util.Map;

public class FormDataView {
    static FormDataView formDataView = null;
    FormData formData;

    private FormDataView() {

    }

    public static FormDataView getInstance() {
        if (formDataView == null)
            formDataView = new FormDataView();
        return formDataView;
    }

    public void setFormData(FormData formData) {
        this.formData = formData;
    }

    public void showFormData() {
        HashMap<String, String> fields = formData.getFields();
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
