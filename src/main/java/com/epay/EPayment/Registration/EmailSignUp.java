package com.epay.EPayment.Registration;

import com.epay.EPayment.Controller.UserController;
import com.epay.EPayment.Models.User;

public class EmailSignUp implements SignUp {
    private void checkEmail(User user) throws Exception {
        UserController userController = UserController.getInstance();
        if (userController.checkExistEmail(user))
            throw new Exception("This Email is registered before.");
    }

    private void checkUsername(User user) throws Exception {
        UserController userController = UserController.getInstance();
        if (userController.checkExistUsername(user))
            throw new Exception("Username need to be unique.");
    }

    @Override
    public void signUp(User user) throws Exception {
        UserController userController = UserController.getInstance();
        checkEmail(user);
        checkUsername(user);
        userController.addUser(user);
    }

}
