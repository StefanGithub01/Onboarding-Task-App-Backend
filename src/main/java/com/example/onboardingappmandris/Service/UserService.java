package com.example.onboardingappmandris.Service;

import com.example.onboardingappmandris.DTO.UserDTO;
import com.example.onboardingappmandris.Entity.User;
import com.example.onboardingappmandris.Exceptions.Login.IncorrectPasswordException;
import com.example.onboardingappmandris.Exceptions.Register.UserNameTakenException;
import com.example.onboardingappmandris.Exceptions.Login.UserNotFoundException;
import com.example.onboardingappmandris.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getAllUsers() {
        return  userRepository.findAll().stream()
                .map(user -> convertToDTO(user))
                .toList();
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUsername());
        return userDTO;
    }

    public Long authenticateUser(String username, String password) {
       Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
       User user = optionalUser.orElseThrow(() -> new UserNotFoundException("User not found"));
       if(!passwordEncoder.matches(password, user.getPassword())) {
           throw new IncorrectPasswordException("Incorrect password");
       }
       return user.getId();
    }

    public void registerUser(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new UserNameTakenException("An account with this username already exists");
        }
        User user = new User(username, passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
