package com.habialtx3.ecommerce_be.repository;

import com.habialtx3.ecommerce_be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findFirstByToken(String token);
}
