package com.epay.EPayment;

import com.epay.EPayment.Controller.*;
import com.epay.EPayment.Models.Admin;
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

@SpringBootApplication
public class EPaymentApplication {
    @PostConstruct
    public void initialize(){
        UserController userController = UserController.getInstance();
        CustomerController customerController = CustomerController.getInstance();
        AdminController adminController = AdminController.getInstance();
        ServiceController serviceController = ServiceController.getInstance();

        userController.addUser(new Admin("admin@epay.com", "admin", "admin"));
        PaymentController paymentController = PaymentController.getInstance();
        paymentController.addPayment(new CreditCardPayment(new PaymentDetails()));
        paymentController.addPayment(new WalletPayment(new PaymentDetails()));
        paymentController.addPayment(new CashPayment(new PaymentDetails()));
        serviceController.addService(new MobileRechargeService());
        serviceController.addService(new InternetPaymentService());
        serviceController.addService(new LandlineService());
        serviceController.addService(new DonationsService());

    }

    public static void main(String[] args) {

        SpringApplication.run(EPaymentApplication.class, args);
    }
}

// http://localhost:8080