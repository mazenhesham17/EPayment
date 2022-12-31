package com.epay.EPayment.Controller;

import com.epay.EPayment.Models.Customer;
import com.epay.EPayment.Transaction.ChargeTransaction;
import com.epay.EPayment.Transaction.PaymentTransaction;
import com.epay.EPayment.Transaction.RefundTransaction;
import com.epay.EPayment.Transaction.Transaction;
import com.epay.EPayment.Util.Container;
import com.epay.EPayment.WebView.ChargeTransactionWebView;
import com.epay.EPayment.WebView.PaymentTransactionWebView;
import com.epay.EPayment.WebView.RefundTransactionWebView;

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

    public int getID() {
        return transaction.getId();
    }

    public double getAmount() {
        return transaction.getAmount();
    }

    public void setRequested() {
        transaction.setRequested(true);
    }

    public Transaction chooseTransaction(int id) throws Exception {
        CustomerController customerController = CustomerController.getInstance();
        Vector<Transaction> transactions = customerController.getTransactions();
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id) {
                if (transaction instanceof RefundTransaction)
                    throw new Exception("Can not make refund request for refund transaction !!");
                if (transaction.isRequested())
                    throw new Exception("Can not make refund request for the same transaction twice !!");
                return transaction;
            }
        }
        throw new Exception("Can not find transaction with id " + id);
    }


    public int getId() {
        return transaction.getId();
    }

    public Customer getCustomer() {
        return transaction.getCustomer();
    }

    public Vector<Container> getTransactions() throws Exception {
        Vector<Container> containers = new Vector<>();
        ChargeTransactionWebView chargeTransactionWebView = ChargeTransactionWebView.getInstance();
        RefundTransactionWebView refundTransactionWebView = RefundTransactionWebView.getInstance();
        PaymentTransactionWebView paymentTransactionWebView = PaymentTransactionWebView.getInstance();
        CustomerController customerController = CustomerController.getInstance();
        Vector<Transaction> transactions = customerController.getTransactions();
        for (Transaction transaction : transactions) {
            if (transaction instanceof PaymentTransaction paymentTransaction) {
                Container container = paymentTransactionWebView.showPaymentTransaction(customerController.getUserName(),
                        paymentTransaction.getDate(), paymentTransaction.getId(),
                        paymentTransaction.getTransactionType(), paymentTransaction.getPaymentMethod(),
                        paymentTransaction.getServiceName(),
                        paymentTransaction.getFormData(),
                        paymentTransaction.getBeforeDiscount(), paymentTransaction.getAmount());
                containers.add(container);
            } else if (transaction instanceof ChargeTransaction chargeTransaction) {
                CreditCardController creditCardController = CreditCardController.getInstance();
                creditCardController.setCreditCard(chargeTransaction.getCreditCard());
                Container container = chargeTransactionWebView.showChargeTransaction(customerController.getUserName(),
                        chargeTransaction.getDate(), chargeTransaction.getId(),
                        chargeTransaction.getTransactionType(), chargeTransaction.getPaymentMethod(),
                        creditCardController.getName(),
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
