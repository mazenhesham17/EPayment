package com.epay.EPayment.Models;

import java.util.Vector;

public class User {
    String email;
    String password;
    String username;

    int notifications;
    int id;

    Vector<Refund> refunds;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        notifications = 0;
        refunds = new Vector<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getNotifications() {
        return notifications;
    }

    public void increment() {
        notifications++;
    }

    public void decrement() {
        notifications--;
    }

    public void clear() {
        notifications = 0;
    }

    public Vector<Refund> getRefunds() {
        return refunds;
    }
}
