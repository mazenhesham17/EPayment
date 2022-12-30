package com.epay.EPayment.Controller;

import com.epay.EPayment.Models.Transaction;
import com.epay.EPayment.Transaction.ChargeTransaction;
import com.epay.EPayment.Transaction.PaymentTransaction;
import com.epay.EPayment.Transaction.RefundTransaction;
import com.epay.EPayment.Util.Container;
import com.epay.EPayment.View.ChargeTransactionWebView;
import com.epay.EPayment.View.PaymentTransactionWebView;
import com.epay.EPayment.View.RefundTransactionWebView;

import java.util.Vector;

public class TransactionController {
    static TransactionController transactionController = null;
    Transaction transaction;

    private TransactionController() {

    }

    public static TransactionController getInstance() {
        if (transactionController == null)
            transactionController = new TransactionController();
        return transactionController;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Vector<Container> getTransactions() throws Exception {
        Vector<Container> containers = new Vector<>();
        ChargeTransactionWebView chargeTransactionWebView = ChargeTransactionWebView.getInstance();
        RefundTransactionWebView refundTransactionWebView = RefundTransactionWebView.getInstance();
        PaymentTransactionWebView paymentTransactionWebView = PaymentTransactionWebView.getInstance();
        CustomerController customerController = CustomerController.getInstance();
        Vector<Transaction> transactions = customerController.getTransactions();
        for (Transaction transaction : transactions) {
            if (transaction instanceof PaymentTransaction) {
                PaymentTransaction paymentTransaction = (PaymentTransaction) transaction;
                Container container = paymentTransactionWebView.showPaymentTransaction(customerController.getUserName(),
                        paymentTransaction.getDate(), paymentTransaction.getId(),
                        paymentTransaction.getTransactionType(), paymentTransaction.getPaymentMethod(),
                        paymentTransaction.getServiceName(),
                        paymentTransaction.getFormData(),
                        paymentTransaction.getBeforeDiscount(), paymentTransaction.getAmount());
                containers.add(container);
            } else if (transaction instanceof ChargeTransaction) {
                ChargeTransaction chargeTransaction = (ChargeTransaction) transaction;
                Container container = chargeTransactionWebView.showChargeTransaction(customerController.getUserName(),
                        chargeTransaction.getDate(), chargeTransaction.getId(),
                        chargeTransaction.getTransactionType(), chargeTransaction.getPaymentMethod(),
                        chargeTransaction.getCreditCard().getName(),
                        chargeTransaction.getAmount());
                containers.add(container);
            } else {
                RefundTransaction refundTransaction = (RefundTransaction) transaction;
                Container container = refundTransactionWebView.showRefundTransaction(customerController.getUserName(),
                        refundTransaction.getDate(), refundTransaction.getId(),
                        refundTransaction.getTransactionType(), refundTransaction.getPaymentMethod(),
                        refundTransaction.getAmount(), refundTransaction.getRefundedID());
                containers.add(container);
            }
        }
        if (containers.isEmpty())
            throw new Exception("There is no Transaction :(");
        return containers;
    }
}
