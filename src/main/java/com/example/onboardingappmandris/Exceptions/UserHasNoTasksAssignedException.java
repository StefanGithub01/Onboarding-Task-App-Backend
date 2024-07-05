package com.example.onboardingappmandris.Exceptions;

public class UserHasNoTasksAssignedException extends RuntimeException{
    public UserHasNoTasksAssignedException(String message) {
        super(message);
    }
}
