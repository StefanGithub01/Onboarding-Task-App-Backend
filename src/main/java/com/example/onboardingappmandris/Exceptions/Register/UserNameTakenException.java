package com.example.onboardingappmandris.Exceptions.Register;

public class UserNameTakenException extends RuntimeException {
    public UserNameTakenException(String message) {
        super(message);
    }
}
