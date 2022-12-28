package com.epay.EPayment.Models;

import java.util.HashMap;

public class FormData {
    HashMap<String, String> fields;

    FormData() {
        fields = new HashMap<>();
    }

    public HashMap<String, String> getFields() {
        return fields;
    }
}
