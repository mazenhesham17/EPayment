package com.epay.EPayment.Controller;

import com.epay.EPayment.DataSet.UserData;
import com.epay.EPayment.Models.Admin;
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

    User user ;


    public String getUsername(){
        return user.getUsername() ;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public static UserController getInstance() {
        if (userController == null)
            userController = new UserController();
        return userController;
    }

    public boolean isSigned(){
        return user != null ;
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

    public User getUser() {
        return user;
    }

    public boolean isAdmin(){
        return user instanceof Admin ;
    }

    public boolean isCustomer(){
        return user instanceof Customer ;
    }

    public void signIn(User user) throws Exception {
        if ( isSigned() ){
            throw new Exception("There is already signed user :(") ;
        }
        SignIn signIn = new EmailSignIn();
        this.user = signIn.signIn(user) ;
    }

    public void signUp(User user) throws Exception {
        if ( isSigned() ){
            throw new Exception("There is already signed user :(") ;
        }
        SignUp signUp = new EmailSignUp();
        signUp.signUp(user);
    }

    public void signOut() throws Exception {
        if ( user == null ){
            throw new Exception("You are already signed out") ;
        }
        user = null ;
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
