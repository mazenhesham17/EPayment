package com.epay.EPayment.Models;

import java.util.HashMap;

public class PaymentDetails {
    HashMap<String, String> details;

    public PaymentDetails() {
        details = new HashMap<>();
    }

    public void setDetail(String detail, String value) {
        details.put(detail, value);
    }

    public String getDetail(String detail) {
        return details.get(detail);
    }
}
