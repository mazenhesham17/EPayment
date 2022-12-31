package com.epay.EPayment.Controller;

import com.epay.EPayment.DataSet.PaymentData;
import com.epay.EPayment.Models.Balance;
import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.View.PaymentView;

import java.util.Vector;

public class PaymentController {
    static PaymentController paymentController = null;
    Payment payment;

    private PaymentController() {
    }

    public static PaymentController getInstance() {
        if (paymentController == null)
            paymentController = new PaymentController();
        return paymentController;
    }

    public Vector<Payment> getPayments() {
        PaymentData paymentData = PaymentData.getInstance();
        return paymentData.getPayments();
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void addPayment(Payment payment) {
        PaymentData paymentData = PaymentData.getInstance();
        int id = paymentData.getPayments().size() + 1;
        payment.setId(id);
        paymentData.getPayments().add(payment);
    }

    public void setCost(double cost) {
        payment.setCost(cost);
    }

    public void setPassword(String password) {
        payment.setPassword(password);
    }

    public void showPayments(Vector<Payment> payments) {
        PaymentView paymentView = PaymentView.getInstance();
        paymentView.showPayments(payments);
    }

    public void setBalance(Balance balance) {
        payment.setBalance(balance);
    }

    public void pay() throws Exception {
        payment.pay();
    }

    public Payment getPayment(int index) {
        PaymentData paymentData = PaymentData.getInstance();
        return paymentData.getPayments().get(index - 1);
    }
}
