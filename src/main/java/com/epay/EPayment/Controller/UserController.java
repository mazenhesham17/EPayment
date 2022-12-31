package com.epay.EPayment.Controller;

import com.epay.EPayment.DataSet.UserData;
import com.epay.EPayment.Models.Admin;
import com.epay.EPayment.Models.Customer;
import com.epay.EPayment.Models.User;
import com.epay.EPayment.Registration.EmailSignIn;
import com.epay.EPayment.Registration.EmailSignUp;
import com.epay.EPayment.Registration.SignIn;
import com.epay.EPayment.Registration.SignUp;
import com.epay.EPayment.Util.Container;
import com.epay.EPayment.View.UserWebView;

import java.util.Vector;

public class UserController {
    static UserController userController = null;
    User user;

    private UserController() {
    }

    public static UserController getInstance() {
        if (userController == null)
            userController = new UserController();
        return userController;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public boolean isSigned() {
        return user != null;
    }

    public void addUser(User user) {
        UserData userData = UserData.getInstance();
        Vector<User> users = userData.getUsers();
        int id = users.size() + 1;
        user.setId(id);
        users.add(user);
    }

    public boolean checkExistEmail(User user) {
        UserData userData = UserData.getInstance();
        Vector<User> users = userData.getUsers();
        for (User value : users) {
            if (value.getEmail().equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkExistUsername(User user) {
        UserData userData = UserData.getInstance();
        Vector<User> users = userData.getUsers();
        for (User value : users) {
            if (value.getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public User checkPassword(User user) {
        UserData userData = UserData.getInstance();
        Vector<User> users = userData.getUsers();
        for (User value : users) {
            if (value.getEmail().equals(user.getEmail()) && value.getPassword().equals(user.getPassword())) {
                return value;
            }
        }
        return null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAdmin() {
        return user instanceof Admin;
    }

    public boolean isCustomer() {
        return user instanceof Customer;
    }

    public void signIn(User user) throws Exception {
        if (isSigned()) {
            throw new Exception("There is already signed user :(");
        }
        SignIn signIn = new EmailSignIn();
        this.user = signIn.signIn(user);
    }

    public void signUp(User user) throws Exception {
        if (isSigned()) {
            throw new Exception("There is already signed user :(");
        }
        SignUp signUp = new EmailSignUp();
        signUp.signUp(user);
    }

    public void signOut() throws Exception {
        if (user == null) {
            throw new Exception("You are already signed out");
        }
        user = null;
    }

    public Vector<Container> getUsers() {
        UserData userData = UserData.getInstance();
        UserWebView userWebView = UserWebView.getInstance();
        Vector<User> users = userData.getUsers();
        Vector<Container> containers = new Vector<>();
        for (User conocreteUser : users) {
            containers.add(userWebView.showUser(conocreteUser.getUsername(), conocreteUser.getEmail(), conocreteUser.getId()));
        }
        return containers;
    }

    public Vector<Customer> getCustomers() {
        UserData userData = UserData.getInstance();
        Vector<Customer> result = new Vector<>();
        Vector<User> users = userData.getUsers();
        for (User user : users) {
            if (user instanceof Customer) {
                result.add((Customer) user);
            }
        }
        return result;
    }

    public Customer chooseCustomer(int id) throws Exception {
        UserData userData = UserData.getInstance();
        if (id < 1 || id > userData.getUsers().size())
            throw new Exception("Customer id not in the range from 1 to " + userData.getUsers().size());
        User user = userData.getUsers().get(id - 1);
        if (user instanceof Admin)
            throw new Exception("Admin don't have any Transactions");
        return (Customer) user;
    }

    public Vector<Container> getWebCustomers() throws Exception {
        UserData userData = UserData.getInstance();
        UserWebView userWebView = UserWebView.getInstance();
        Vector<User> users = userData.getUsers();
        Vector<Container> containers = new Vector<>();
        for (User conocreteUser : users) {
            if (conocreteUser instanceof Customer) {
                containers.add(userWebView.showUser(conocreteUser.getUsername(), conocreteUser.getEmail(), conocreteUser.getId()));
            }
        }
        if (containers.isEmpty())
            throw new Exception("There is no Customers");
        return containers;
    }
}
