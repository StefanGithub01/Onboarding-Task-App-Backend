package com.example.onboardingappmandris.Exceptions.Login;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
