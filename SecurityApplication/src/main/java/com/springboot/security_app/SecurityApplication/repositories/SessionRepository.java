package com.springboot.security_app.SecurityApplication.repositories;

import com.springboot.security_app.SecurityApplication.entities.Session;
import com.springboot.security_app.SecurityApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByUser(User user);

    Optional<Session> findByRefreshToken(String refreshToken);
}