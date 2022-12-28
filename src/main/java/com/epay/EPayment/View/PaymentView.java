package com.epay.EPayment.View;

import com.epay.EPayment.Models.Payment;

import java.util.Vector;

public class PaymentView {
    static PaymentView paymentView = null;
    Payment payment;

    private PaymentView() {
    }

    public static PaymentView getInstance() {
        if (paymentView == null)
            paymentView = new PaymentView();
        return paymentView;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void showPayments(Vector<Payment> payments) {
        for (int i = 0; i < payments.size(); i++) {
            System.out.println((i + 1) + ". " + payments.get(i).getName());
        }
    }
}
