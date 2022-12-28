package com.epay.EPayment.Controller;

import com.epay.EPayment.DataSet.UserData;
import com.epay.EPayment.Models.Customer;
import com.epay.EPayment.Models.User;
import com.epay.EPayment.Registration.EmailSignIn;
import com.epay.EPayment.Registration.EmailSignUp;
import com.epay.EPayment.Registration.SignIn;
import com.epay.EPayment.Registration.SignUp;

import java.util.Vector;

public class UserController {
    static UserController userController = null;

    private UserController() {
    }

    public static UserController getInstance() {
        if (userController == null)
            userController = new UserController();
        return userController;
    }

    public void addUser(User user) {
        UserData userData = UserData.getInstance();
        Vector<User> users = userData.getUsers();
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

    public User signIn(User user) throws Exception {
        SignIn signIn = new EmailSignIn();
        return signIn.signIn(user);
    }

    public void signUp(User user) throws Exception {
        SignUp signUp = new EmailSignUp();
        signUp.signUp(user);
    }

    public User signOut() {
        return null;
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
}
