package com.epay.EPayment.Controller;

import com.epay.EPayment.DataSet.UserData;
import com.epay.EPayment.Discount.Discount;
import com.epay.EPayment.Models.Admin;
import com.epay.EPayment.Models.Customer;
import com.epay.EPayment.Models.Refund;
import com.epay.EPayment.Models.User;
import com.epay.EPayment.Service.Service;
import com.epay.EPayment.Util.Container;

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

    public void addSpecificDiscount(Discount discount, int id) throws Exception {
        DiscountController discountController = DiscountController.getInstance();
        UserData userData = UserData.getInstance();
        Vector<User> users = userData.getUsers();
        CategoryController categoryController = CategoryController.getInstance();
        Service service = categoryController.getCategory(id, 0);
        CustomerController customerController = CustomerController.getInstance();

        for (User user : users) {
            if (user instanceof Customer customer) {
                customerController.setCustomer(customer);
                discountController.setDiscountData(customerController.getDiscountData());
                discountController.addSpecificDiscount(discount, service);
            }
        }
    }

    public void addOverallDiscount(Discount discount) {
        DiscountController discountController = DiscountController.getInstance();
        UserData userData = UserData.getInstance();
        Vector<User> users = userData.getUsers();
        CustomerController customerController = CustomerController.getInstance();
        for (User user : users) {
            if (user instanceof Customer customer) {
                customerController.setCustomer(customer);
                discountController.setDiscountData(customerController.getDiscountData());
                discountController.addOverallDiscount(discount);
            }
        }
    }

    public Vector<Container> getRefunds() throws Exception {
        if (admin.getRefunds().isEmpty())
            throw new Exception("There is no refund requests :)");
        RefundController refundController = RefundController.getInstance();
        admin.clear();
        return refundController.getRefunds(admin.getRefunds());
    }

    public Refund chooseRefund(int id) throws Exception {
        Vector<Refund> refunds = admin.getRefunds();
        for (Refund refund : refunds) {
            RefundController refundController = RefundController.getInstance();
            refundController.setRefund(refund);
            if (refundController.getId() == id)
                return refund;
        }
        throw new Exception("There is no refund with id " + id);
    }

    private void notifyOthers() {
        UserData userData = UserData.getInstance();
        Vector<User> users = userData.getUsers();
        for (User user : users) {
            if (user instanceof Admin adminUser) {
                if (adminUser != admin) {
                    adminUser.decrement();
                }
            }
        }
    }

    public void acceptRefund(Refund refund) throws Exception {
        RefundController refundController = RefundController.getInstance();
        refundController.setRefund(refund);
        if (!refundController.isPending())
            throw new Exception("You can not change the state of already changed refund !!");
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
        if (!refundController.isPending())
            throw new Exception("You can not change the state of already changed refund !!");
        refundController.rejectRefund();
        CustomerController customerController = CustomerController.getInstance();
        customerController.setCustomer(refundController.getCustomer());
        customerController.update();
        notifyOthers();
    }

    public void update(Refund refund) {
        admin.getRefunds().add(refund);
        admin.increment();
    }

    public Customer chooseCustomer(int id) throws Exception {
        UserController userController = UserController.getInstance();
        return userController.chooseCustomer(id);
    }

    public Vector<Container> getCustomers() throws Exception {
        UserController userController = UserController.getInstance();
        return userController.getWebCustomers();
    }
}
