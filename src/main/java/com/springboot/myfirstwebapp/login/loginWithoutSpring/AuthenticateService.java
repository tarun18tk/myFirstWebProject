package com.springboot.myfirstwebapp.login.loginWithoutSpring;

import org.springframework.stereotype.Service;

// @Component can be used but,
@Service // becuase this is buieness logic
public class AuthenticateService {

    public boolean authenticate(String username, String password) {
        boolean isValidUsername = username.equalsIgnoreCase("Tarun");
        boolean isValidPassword = password.equalsIgnoreCase("password");
        return isValidPassword && isValidUsername;
    }

}