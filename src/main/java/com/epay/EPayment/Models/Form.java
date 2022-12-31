package com.epay.EPayment.Models;

import java.util.HashMap;

public class Form {
    HashMap<String, String[]> fields;

    public Form() {
        fields = new HashMap<>();
    }

    public HashMap<String, String[]> getFields() {
        return fields;
    }

}
