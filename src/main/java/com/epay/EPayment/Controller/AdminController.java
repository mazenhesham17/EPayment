package com.epay.EPayment.Controller;

import com.epay.EPayment.DataSet.UserData;
import com.epay.EPayment.Models.*;
import com.epay.EPayment.View.AdminView;

import java.util.Vector;

public class AdminController {
    static AdminController adminController = null;
    Admin admin;

    private AdminController() {
    }

    public static AdminController getInstance() {
        if (adminController == null)
            adminController = new AdminController();
        return adminController;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void addSpecificDiscount(Discount discount, Service service) {
        DiscountController discountController = DiscountController.getInstance();
        UserData userData = UserData.getInstance();
        Vector<User> users = userData.getUsers();
        for (User user : users) {
            if (user instanceof Customer) {
                Customer customer = (Customer) user;
                discountController.setDiscountData(customer.getDiscountData());
                discountController.addSpecificDiscount(discount, service);
            }
        }
    }

    public void addOverallDiscount(Discount discount) {
        DiscountController discountController = DiscountController.getInstance();
        UserData userData = UserData.getInstance();
        Vector<User> users = userData.getUsers();
        for (User user : users) {
            if (user instanceof Customer) {
                Customer customer = (Customer) user;
                discountController.setDiscountData(customer.getDiscountData());
                discountController.addOverallDiscount(discount);
            }
        }
    }

    public void checkNotifications() {
        if (admin.getNotifications() != 0) {
            AdminView adminView = AdminView.getInstance();
            adminView.setAdmin(admin);
            adminView.showUpdates();
        }
    }

    public Vector<Refund> showPendingRefunds() {
        AdminView adminView = AdminView.getInstance();
        adminView.setAdmin(admin);
        admin.clear();
        return adminView.showPendingRefunds();
    }

    private void notifyOthers() {
        UserData userData = UserData.getInstance();
        Vector<User> users = userData.getUsers();
        for (User user : users) {
            if (user instanceof Admin) {
                Admin adminUser = (Admin) user;
                if (adminUser != admin) {
                    adminUser.decrement();
                }
            }
        }
    }

    public void acceptRefund(Refund refund) throws Exception {
        RefundController refundController = RefundController.getInstance();
        refundController.setRefund(refund);
        refundController.acceptRefund();
        CustomerController customerController = CustomerController.getInstance();
        customerController.setCustomer(refundController.getCustomer());
        customerController.refund(refund);
        customerController.update();
        notifyOthers();
    }

    public void rejectRefund(Refund refund) throws Exception {
        RefundController refundController = RefundController.getInstance();
        refundController.setRefund(refund);
        refundController.rejectRefund();
        CustomerController customerController = CustomerController.getInstance();
        customerController.setCustomer(refundController.getCustomer());
        customerController.refund(refund);
        customerController.update();
        notifyOthers();
    }

    public void update(Refund refund) {
        admin.getRefunds().add(refund);
        admin.increment();
    }
}
