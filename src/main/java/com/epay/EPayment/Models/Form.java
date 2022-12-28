package com.epay.EPayment.Models;

import java.util.HashMap;

public class Form {
    HashMap<String, String[]> fields;

    Form() {
        fields = new HashMap<>();
    }

    public HashMap<String, String[]> getFields() {
        return fields;
    }

}
