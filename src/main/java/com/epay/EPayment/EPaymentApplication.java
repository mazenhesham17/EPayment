package com.epay.EPayment;

import com.epay.EPayment.Controller.CategoryController;
import com.epay.EPayment.Controller.PaymentController;
import com.epay.EPayment.Controller.UserController;
import com.epay.EPayment.Models.Admin;
import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.Models.PaymentDetails;
import com.epay.EPayment.Payment.CashPayment;
import com.epay.EPayment.Payment.CreditCardPayment;
import com.epay.EPayment.Payment.WalletPayment;
import com.epay.EPayment.Service.DonationsService;
import com.epay.EPayment.Service.InternetPaymentService;
import com.epay.EPayment.Service.LandlineService;
import com.epay.EPayment.Service.MobileRechargeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Vector;

@SpringBootApplication
public class EPaymentApplication {
    public static void main(String[] args) {

        SpringApplication.run(EPaymentApplication.class, args);
    }

    @PostConstruct
    public void initialize() {
        UserController userController = UserController.getInstance();
        CategoryController categoryController = CategoryController.getInstance();

        userController.addUser(new Admin("admin@epay.com", "admin", "admin"));
        PaymentController paymentController = PaymentController.getInstance();
        paymentController.addPayment(new CreditCardPayment(new PaymentDetails()));
        paymentController.addPayment(new WalletPayment(new PaymentDetails()));
        paymentController.addPayment(new CashPayment(new PaymentDetails()));
        Vector<Payment> payments = new Vector<>();
        payments.add(paymentController.getPayment(1));
        payments.add(paymentController.getPayment(2));
        Vector<String> companies = new Vector<>();
        companies.add("Etisalat");
        companies.add("Vodafone");
        companies.add("Orange");
        companies.add("We");
        // mobile
        categoryController.addCategory(new MobileRechargeService("Mobile Recharge", "Amount", payments), companies);

        // internet
        categoryController.addCategory(new InternetPaymentService("Internet Payment", "Internet bundle", payments), companies);
        companies = new Vector<>();
        companies.add("Quarter Bill");
        companies.add("Monthly Bill");

        // landline
        categoryController.addCategory(new LandlineService("Landline", "Landline Bill", payments), companies);

        // donations
        payments = new Vector<>();
        companies = new Vector<>();
        payments.add(paymentController.getPayment(1));
        payments.add(paymentController.getPayment(2));
        payments.add(paymentController.getPayment(3));
        companies.add("Cancer Hospital");
        companies.add("Schools");
        companies.add("NGOs( Non Profitable Organizations)");
        categoryController.addCategory(new DonationsService("Donation", "Monthly Donation", payments), companies);
    }
}

// http://localhost:8080