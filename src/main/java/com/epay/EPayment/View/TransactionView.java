package com.epay.EPayment.View;

import com.epay.EPayment.Controller.FormDataController;
import com.epay.EPayment.Models.Transaction;
import com.epay.EPayment.Transaction.ChargeTransaction;
import com.epay.EPayment.Transaction.PaymentTransaction;
import com.epay.EPayment.Transaction.RefundTransaction;

public class TransactionView {
    static TransactionView transactionView = null;
    Transaction transaction;

    private TransactionView() {
    }

    public static TransactionView getInstance() {
        if (transactionView == null)
            transactionView = new TransactionView();
        return transactionView;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void showTransactionDetails() {
        System.out.println("Username : " + transaction.getCustomer().getUsername());
        System.out.println("Transaction Time : " + transaction.getDate());
        System.out.println("Transaction ID : " + transaction.getId());
        System.out.println("Transaction Type : " + transaction.getTransactionType());
        System.out.println("Payment Method : " + transaction.getPaymentMethod());
        if (transaction instanceof PaymentTransaction) {
            PaymentTransaction paymentTransaction = (PaymentTransaction) transaction;
            System.out.println("Service Name : " + paymentTransaction.getServiceName());
            FormDataController formDataController = FormDataController.getInstance();
            formDataController.setFormData(paymentTransaction.getFormData());
            formDataController.showFormData();
            System.out.println("Before Discount : " + paymentTransaction.getBeforeDiscount());
            System.out.println("After Discount : " + transaction.getAmount());
        } else if (transaction instanceof ChargeTransaction) {
            ChargeTransaction chargeTransaction = (ChargeTransaction) transaction;
            System.out.println("Card Name : " + chargeTransaction.getCreditCard().getName());
            System.out.println("Charged Amount : " + transaction.getAmount());
        } else {
            RefundTransaction refundTransaction = (RefundTransaction) transaction;
            System.out.println("Refunded Transaction ID : " + refundTransaction.getRefundedID());
            System.out.println("Amount Refunded : " + transaction.getAmount());
        }


    }
}
