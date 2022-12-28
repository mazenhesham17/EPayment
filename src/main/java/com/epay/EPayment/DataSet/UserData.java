package com.epay.EPayment.DataSet;

import com.epay.EPayment.Models.User;

import java.util.Vector;

public class UserData {
    static UserData userData = null;
    Vector<User> users;

    private UserData() {
        users = new Vector<>();
    }

    public static UserData getInstance() {
        if (userData == null)
            userData = new UserData();
        return userData;
    }

    public Vector<User> getUsers() {
        return users;
    }

}
