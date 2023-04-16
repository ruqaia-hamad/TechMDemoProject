package com.TechM.demoProject.RequestObject;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UserRequest {
    private String userName;

    private String email;

    private String mobileNumber;

    private String password;
    private String confirmPassword;
}
