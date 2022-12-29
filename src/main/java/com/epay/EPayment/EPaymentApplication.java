package com.epay.EPayment;

import com.epay.EPayment.API.Initializer;
import com.epay.EPayment.API.UserAPI;
import com.epay.EPayment.Models.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EPaymentApplication {

    public static void main(String[] args) {

        SpringApplication.run(EPaymentApplication.class, args);
        Initializer initializer = new Initializer();
    }
}

// http://localhost:8080