package com.epay.EPayment.Registration;

import com.epay.EPayment.Controller.UserController;
import com.epay.EPayment.Models.User;

public class EmailSignIn implements SignIn {

    private void checkEmail(User user) throws Exception {
        UserController userController = UserController.getInstance();
        if (!userController.checkExistEmail(user))
            throw new Exception("This Email does not exist !!");
    }

    private User checkPassword(User user) throws Exception {
        UserController userController = UserController.getInstance();
        user = userController.checkPassword(user);
        if (user == null)
            throw new Exception("The password is incorrect");
        return user;
    }

    @Override
    public User signIn(User user) throws Exception {
        checkEmail(user);
        user = checkPassword(user);
        System.out.println("Welcome " + user.getUsername() + " :)");
        return user;
    }

}
