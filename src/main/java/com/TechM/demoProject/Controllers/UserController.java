package com.TechM.demoProject.Controllers;


import com.TechM.demoProject.Models.Users;
import com.TechM.demoProject.RequestObject.UserRequest;
import com.TechM.demoProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")

    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) {
        try {
            Users user = userService.registerUser(userRequest);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
}}
//