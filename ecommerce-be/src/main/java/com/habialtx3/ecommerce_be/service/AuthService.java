package com.habialtx3.ecommerce_be.service;

import com.habialtx3.ecommerce_be.entity.User;
import com.habialtx3.ecommerce_be.model.auth.LoginRequest;
import com.habialtx3.ecommerce_be.model.auth.RegisterRequest;
import com.habialtx3.ecommerce_be.model.auth.TokenResponse;
import com.habialtx3.ecommerce_be.model.web.WebResponse;
import com.habialtx3.ecommerce_be.repository.UserRepository;
import com.habialtx3.ecommerce_be.security.Bcrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private ValidationService validation;

    @Autowired
    private UserRepository userRepository;

    public TokenResponse login(LoginRequest request) {
        validation.validate(request);

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Username or password wrong")
        );

        if (Bcrypt.checkpw(request.getPassword(), user.getPassword())) {
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30Days());

            userRepository.save(user);

            return TokenResponse.builder()
                    .token(user.getToken())
                    .tokenExpiredAt(user.getTokenExpiredAt())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username or password wrong");
        }
    }

    private long next30Days() {
        return System.currentTimeMillis() + (1000 * 60 * 24 * 30);
    }

}
