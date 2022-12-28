package com.epay.EPayment.DataSet;

import com.epay.EPayment.Models.Payment;

import java.util.Vector;

public class PaymentData {
    static PaymentData paymentData = null;
    Vector<Payment> payments;

    private PaymentData() {
        payments = new Vector<>();
    }

    public static PaymentData getInstance() {
        if (paymentData == null)
            paymentData = new PaymentData();
        return paymentData;
    }

    public Vector<Payment> getPayments() {
        return payments;
    }
}
