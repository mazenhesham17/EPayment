package com.epay.EPayment.Controller;

import com.epay.EPayment.Balance.CreditCard;
import com.epay.EPayment.Balance.Wallet;
import com.epay.EPayment.DataSet.UserData;
import com.epay.EPayment.Models.*;
import com.epay.EPayment.Search.ConcreteSearch;
import com.epay.EPayment.Search.Search;
import com.epay.EPayment.Transaction.ChargeTransaction;
import com.epay.EPayment.Transaction.PaymentTransaction;
import com.epay.EPayment.Transaction.RefundTransaction;
import com.epay.EPayment.Util.Container;
import com.epay.EPayment.View.CustomerView;

import java.util.HashMap;
import java.util.Vector;

public class CustomerController {

    static CustomerController customerController = null;
    Customer customer;

    private CustomerController() {
    }

    public static CustomerController getInstance() {
        if (customerController == null)
            customerController = new CustomerController();
        return customerController;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addCard(CreditCard card) {
        customer.getCards().add(card);
    }

    public Vector<Container> searchServices(String sub) throws Exception {
        Search search = new ConcreteSearch();
        ServiceController serviceController = ServiceController.getInstance() ;
        return serviceController.getWebView(search.find(sub));
    }

    public Service chooseService(Vector<Service> services, int index) {
        ServiceController serviceController = ServiceController.getInstance();
        return serviceController.chooseService(services, index);
    }

    public CreditCard getCard(int index) {
        return customer.getCards().get(index - 1);
    }

    public void chargeWallet(CreditCard card, double cost, String password) throws Exception {
        WalletController walletController = WalletController.getInstance() ;
        walletController.setWallet(customer.getWallet());
        walletController.deposit(card, cost, password);
        System.out.println(cost + " added to your wallet successfully :)");
    }

    public void setFormDataField(String key, String value) {
        ServiceController serviceController = ServiceController.getInstance();
        serviceController.setFormDataField(key, value);
    }

    public Wallet getWallet() {
        return customer.getWallet();
    }

    public HashMap<String, String[]> getFields() {
        ServiceController serviceController = ServiceController.getInstance();
        return serviceController.getFormFields();
    }

    public DiscountData getDiscountData() {
        return customer.getDiscountData();
    }

    public Vector<Container> showAllDiscounts() throws Exception {
        DiscountController discountController = DiscountController.getInstance();
        discountController.setDiscountData(customer.getDiscountData());
        return discountController.showAll();
    }

    public void addTransaction(Transaction transaction) {
        customer.getTransactions().add(transaction);
    }

    public void addRefund(Transaction transaction) {
        Refund refund = new Refund(transaction);
        customer.getRefunds().add(refund);
        notifyAdmins(refund);
    }

    public void checkNotifications() {
        if (customer.getNotifications() != 0) {
            CustomerView customerView = CustomerView.getInstance();
            customerView.setCustomer(customer);
            customerView.showUpdates();
        }
    }

    public void refund(Refund refund) throws Exception {
        Transaction transaction = refund.getTransaction();
        if (transaction instanceof PaymentTransaction) {
            WalletController walletController = WalletController.getInstance() ;
            walletController.setWallet(customer.getWallet());
            walletController.deposit(transaction.getAmount());
        } else if (transaction instanceof ChargeTransaction) {
            CreditCardController creditCardController = CreditCardController.getInstance() ;
            creditCardController.setCreditCard(((ChargeTransaction) transaction).getCreditCard());
            creditCardController.deposit(transaction.getAmount());
        } else {
            throw new Exception("You can not make refund in Refunded requests ");
        }
        Transaction refundTransaction = new RefundTransaction(transaction.getCustomer(), transaction.getAmount(), refund);
        addTransaction(refundTransaction);
    }

    private void notifyAdmins(Refund refund) {
        UserData userData = UserData.getInstance();
        Vector<User> users = userData.getUsers();
        for (User user : users) {
            if (user instanceof Admin) {
                AdminController adminController = AdminController.getInstance();
                adminController.setAdmin((Admin) user);
                adminController.update(refund);
            }
        }
    }

    public void showRefunds() {
        CustomerView customerView = CustomerView.getInstance();
        customerView.setCustomer(customer);
        customerView.showRefunds();
        customer.clear();
    }

    public Vector<Transaction> showRefundableTransactions() {
        CustomerView customerView = CustomerView.getInstance();
        customerView.setCustomer(customer);
        return customerView.showRefundableTransactions();
    }


    public void showAllTransactions() {
        CustomerView customerView = CustomerView.getInstance();
        customerView.setCustomer(customer);
        customerView.showTransactions();
    }

    public void showCustomers(Vector<Customer> customers) {
        CustomerView customerView = CustomerView.getInstance();
        customerView.showCustomers(customers);
    }

    public void update() {
        customer.increment();
    }
}
