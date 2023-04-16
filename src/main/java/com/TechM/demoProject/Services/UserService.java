package com.TechM.demoProject.Services;


import com.TechM.demoProject.Models.Users;
import com.TechM.demoProject.Repositories.UserRepository;
import com.TechM.demoProject.RequestObject.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users registerUser(UserRequest userRequest) {
        Users user = createUser(userRequest);
        return userRepository.save(user);

    }

    private Users createUser(UserRequest userRequest) {
        String mobile_number = userRequest.getMobileNumber();
        validateMobileNumber(mobile_number);

        if (userRepository.existsByMobileNumber(mobile_number)) {
            throw new MobileNumberAlreadyExistsException("Mobile number already exists");
        }
        validateEmail(userRequest.getEmail());
        validatePassword(userRequest.getPassword(), userRequest.getConfirmPassword());

        Users users = new Users();
        users.setUserName(userRequest.getUserName());
        users.setMobileNumber(userRequest.getMobileNumber());
        users.setEmail(userRequest.getEmail());
        String encodedPassword = new BCryptPasswordEncoder().encode(userRequest.getPassword());
        users.setPassword(encodedPassword);
        return users;
    }

    private void validateMobileNumber(String mobileNumber) {
        if (mobileNumber == null || mobileNumber.isEmpty()) {
            throw new IllegalArgumentException("Mobile number is required");
        }


        if (!mobileNumber.matches("^[79]\\d{7}$")) {
            throw new IllegalArgumentException("Invalid mobile number");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    private void validatePassword(String password, String confirmPassword) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }

        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        if (password.length() < 8 || password.length() > 20) {
            throw new IllegalArgumentException("Password must be between 8 and 20 characters");
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter");
        }

        if (!password.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("Password must contain at least one lowercase letter");
        }

        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Password must contain at least one digit");
        }

        if (!password.matches(".*[@#$%^&+=].*")) {
            throw new IllegalArgumentException("Password must contain at least one special character");
        }

    }
}
