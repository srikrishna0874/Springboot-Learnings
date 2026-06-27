package com.springboot.spring_security_learning.services;

import com.springboot.spring_security_learning.entities.Session;
import com.springboot.spring_security_learning.entities.User;
import com.springboot.spring_security_learning.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;


    public Session hasActiveSession(User user) {
        return sessionRepository.findByUser(user).orElse(null);
    }

    public void createSession(String token, User user) {
        Session session = Session.builder()
                .token(token)
                .user(user)
                .build();

        System.out.println("New session created with id " + session.getId());

        sessionRepository.save(session);
    }

    public void deleteExistingSession(User user) {
        sessionRepository.deleteByUser(user);
        System.out.println("User has been deleted");
    }

    public boolean hasActiveSessionWithToken(String token) {
        return sessionRepository.existsByToken(token);
    }
}
