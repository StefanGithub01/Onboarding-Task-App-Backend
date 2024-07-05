package com.example.onboardingappmandris.Exceptions;

public class NoTasksFoundException extends RuntimeException{
    public NoTasksFoundException(String message) {
        super(message);
    }
}
