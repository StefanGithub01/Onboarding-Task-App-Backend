package com.example.onboardingappmandris.Controller;

import com.example.onboardingappmandris.DTO.LoginRequest;
import com.example.onboardingappmandris.Exceptions.Login.IncorrectPasswordException;
import com.example.onboardingappmandris.Exceptions.Register.UserNameTakenException;
import com.example.onboardingappmandris.Exceptions.Login.UserNotFoundException;
import com.example.onboardingappmandris.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody LoginRequest loginRequest) {
        userService.registerUser(loginRequest.getUserName(), loginRequest.getPassword());
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        userService.authenticateUser(loginRequest.getUserName(), loginRequest.getPassword());
        return ResponseEntity.ok("Login successful");
    }
}
