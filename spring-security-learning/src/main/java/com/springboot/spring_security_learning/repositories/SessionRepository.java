package com.springboot.spring_security_learning.repositories;

import com.springboot.spring_security_learning.entities.Session;
import com.springboot.spring_security_learning.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    void deleteByUser(User user);

    boolean existsByToken(String token);

    Optional<Session> findByUser(User user);
}