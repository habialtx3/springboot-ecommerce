package com.habialtx3.ecommerce_be.controller;

import com.habialtx3.ecommerce_be.entity.User;
import com.habialtx3.ecommerce_be.model.auth.LoginRequest;
import com.habialtx3.ecommerce_be.model.auth.RegisterRequest;
import com.habialtx3.ecommerce_be.model.auth.TokenResponse;
import com.habialtx3.ecommerce_be.model.web.WebResponse;
import com.habialtx3.ecommerce_be.service.AuthService;
import com.habialtx3.ecommerce_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    WebResponse<TokenResponse> login(@RequestBody LoginRequest request) {
        TokenResponse response = authService.login(request);

        return WebResponse.<TokenResponse>builder()
                .data(response)
                .build();
    }

    @DeleteMapping(path = "/logout",
            produces = MediaType.APPLICATION_JSON_VALUE)
    WebResponse<String> logout(User user) {
        authService.logout(user);
        return WebResponse.<String>builder()
                .message("Log Out Success")
                .build();
    }
}
