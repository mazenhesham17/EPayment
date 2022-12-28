package com.epay.EPayment.Registration;

import com.epay.EPayment.Models.User;

public interface SignIn {
    User signIn(User user) throws Exception;
}