package com.example.onboardingappmandris.Controller;

import com.example.onboardingappmandris.DTO.LoginRequest;
import com.example.onboardingappmandris.DTO.UserDTO;
import com.example.onboardingappmandris.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody LoginRequest loginRequest) {
        userService.registerUser(loginRequest.getUserName(), loginRequest.getPassword());
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Long> loginUser(@RequestBody LoginRequest loginRequest) {
        Long userId = userService.authenticateUser(loginRequest.getUserName(), loginRequest.getPassword());
        return ResponseEntity.ok(userId);
    }
}
