package com.habialtx3.ecommerce_be.service;

import com.habialtx3.ecommerce_be.entity.Product;
import com.habialtx3.ecommerce_be.entity.User;
import com.habialtx3.ecommerce_be.model.auth.RegisterRequest;
import com.habialtx3.ecommerce_be.model.auth.TokenResponse;
import com.habialtx3.ecommerce_be.model.product.ProductResponse;
import com.habialtx3.ecommerce_be.model.user.UserResponse;
import com.habialtx3.ecommerce_be.model.web.WebResponse;
import com.habialtx3.ecommerce_be.repository.UserRepository;
import com.habialtx3.ecommerce_be.security.Bcrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private ValidationService validation;

    @Autowired
    private UserRepository userRepository;

    private UserResponse toUserResponse(User response) {
        return UserResponse.builder()
                .id(response.getId())
                .fullName(response.getFullName())
                .email(response.getEmail())
                .phone(response.getPhone())
                .role(response.getRole())
                .createdAt(response.getCreatedAt())
                .build();
    }

    @Transactional
    public UserResponse register(RegisterRequest request) {
        validation.validate(request);

        User isEmailExist = userRepository.findByEmail(request.getEmail()).orElse(null);

        if(Objects.nonNull(isEmailExist)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already used");
        }

        User user = new User();

        user.setEmail(request.getEmail());
        user.setPassword(Bcrypt.hashpw(request.getPassword(),Bcrypt.gensalt()));
        user.setPhone(request.getPhone());
        user.setFullName(request.getFullName());
        user.setRole(request.getRole());

        userRepository.save(user);

        return toUserResponse(user);
    }


}
