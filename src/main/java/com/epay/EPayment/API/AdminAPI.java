package com.epay.EPayment.API;

import com.epay.EPayment.DataSet.UserData;
import com.epay.EPayment.Models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Vector;

@RestController
public class AdminAPI {

    @GetMapping("/users")
    public Vector<User> getUsers() {
        UserData userData = UserData.getInstance();
        return userData.getUsers();
    }
}
