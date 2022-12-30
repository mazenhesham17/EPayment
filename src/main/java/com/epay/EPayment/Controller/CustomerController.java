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

    public void addCard(CreditCard card) {
        int id = customer.getCards().size() + 1;
        card.setId(id);
        customer.getCards().add(card);
    }

    public Vector<Container> searchServices(String sub) throws Exception {
        Search search = new ConcreteSearch();
        ServiceController serviceController = ServiceController.getInstance();
        return serviceController.getWebView(search.find(sub));
    }

    public Service chooseService(Vector<Service> services, int index) {
        ServiceController serviceController = ServiceController.getInstance();
        return serviceController.chooseService(services, index);
    }

    public CreditCard getCard(int id) throws Exception {
        if (customer.getCards().isEmpty())
            throw new Exception("You don't have any cards :( ");
        if (id < 1 || id > customer.getCards().size())
            throw new Exception("Id not in the range from 1 to " + customer.getCards().size());
        return customer.getCards().get(id - 1);
    }

    public void chargeWallet(CreditCard card, double cost, String password) throws Exception {
        WalletController walletController = WalletController.getInstance();
        walletController.setWallet(customer.getWallet());
        walletController.deposit(card, cost, password);
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
            WalletController walletController = WalletController.getInstance();
            walletController.setWallet(customer.getWallet());
            walletController.deposit(transaction.getAmount());
        } else if (transaction instanceof ChargeTransaction) {
            CreditCardController creditCardController = CreditCardController.getInstance();
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

//    public Vector<Transaction> showRefundableTransactions() {
//        CustomerView customerView = CustomerView.getInstance();
//        customerView.setCustomer(customer);
//        return customerView.showRefundableTransactions();
//    }

//    public void showAllTransactions() {
//        CustomerView customerView = CustomerView.getInstance();
//        customerView.setCustomer(customer);
//        customerView.showTransactions();
//    }

    public void showCustomers(Vector<Customer> customers) {
        CustomerView customerView = CustomerView.getInstance();
        customerView.showCustomers(customers);
    }

    public void update() {
        customer.increment();
    }

    public Vector<Container> getCards() throws Exception {
        if (customer.getCards().isEmpty())
            throw new Exception("There is no cards :(");
        CreditCardController creditCardController = CreditCardController.getInstance();
        return creditCardController.getCards(customer.getCards());
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vector<Transaction> getTransactions() {
        return customer.getTransactions();
    }

    public String getUserName() {
        return customer.getUsername();
    }
}
