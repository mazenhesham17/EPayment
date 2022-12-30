package com.epay.EPayment.View;

import com.epay.EPayment.Models.Customer;
import com.epay.EPayment.Models.Refund;
import com.epay.EPayment.Models.Transaction;

import java.util.Vector;

public class CustomerView {
    static CustomerView customerView = null;
    Customer customer;

    private CustomerView() {
    }

    public static CustomerView getInstance() {
        if (customerView == null)
            customerView = new CustomerView();
        return customerView;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void showCustomers(Vector<Customer> customers) {
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i).getUsername());
        }
    }

    public void showCustomer() {
        System.out.println("Email : " + customer.getEmail());
        System.out.println("Username : " + customer.getUsername());
    }

    public void showWallet() {
        WalletView walletView = WalletView.getInstance();
        walletView.setWallet(customer.getWallet());
        walletView.showWalletAmount();
    }

    public void showUpdates() {
        System.out.println("You have " + customer.getNotifications() + " new updates");
    }

    public Vector<Transaction> showRefundableTransactions() {
        boolean isFilled = false;
        TransactionView transactionView = TransactionView.getInstance();
        Vector<Transaction> transactions = customer.getTransactions();
        Vector<Transaction> result = new Vector<>();
        for (Transaction transaction : transactions) {
            if (!transaction.isRequested()) {
                System.out.println((result.size() + 1) + ".");
                isFilled = true;
                transactionView.setTransaction(transaction);
                transactionView.showTransactionDetails();
                result.add(transaction);
            }
        }
        if (!isFilled) {
            System.out.println("There is no transactions to refund");
        }
        return result;
    }

    public void showTransactions() {
        boolean isFilled = false;
        TransactionView transactionView = TransactionView.getInstance();
        Vector<Transaction> transactions = customer.getTransactions();
        for (Transaction transaction : transactions) {
            isFilled = true;
            transactionView.setTransaction(transaction);
            transactionView.showTransactionDetails();
        }
        if (!isFilled) {
            System.out.println("There is no transaction yet :(");
        }
    }

    public void showRefunds() {
        boolean isFilled = false;
        RefundView refundView = RefundView.getInstance();
        Vector<Refund> refunds = customer.getRefunds();
        for (Refund refund : refunds) {
            isFilled = true;
            refundView.setRefund(refund);
            refundView.showRefundDetails();
        }
        if (!isFilled) {
            System.out.println("There is no refund requests :)");
        }
    }

}
