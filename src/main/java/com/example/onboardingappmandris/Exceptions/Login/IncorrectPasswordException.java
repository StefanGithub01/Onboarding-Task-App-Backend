package com.example.onboardingappmandris.Exceptions.Login;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
