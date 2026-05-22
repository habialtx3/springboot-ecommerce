package com.habialtx3.ecommerce_be.controller;

import com.habialtx3.ecommerce_be.model.auth.RegisterRequest;
import com.habialtx3.ecommerce_be.model.user.UserResponse;
import com.habialtx3.ecommerce_be.model.web.WebResponse;
import com.habialtx3.ecommerce_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =MediaType.APPLICATION_JSON_VALUE)
    WebResponse<UserResponse> register(@RequestBody RegisterRequest request) {
        UserResponse response = userService.register(request);
        return WebResponse.<UserResponse>builder()
                .data(response)
                .message("user created succesfully")
                .build();
    }

}
