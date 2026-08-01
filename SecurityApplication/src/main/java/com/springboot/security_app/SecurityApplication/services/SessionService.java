package com.springboot.security_app.SecurityApplication.services;

import com.springboot.security_app.SecurityApplication.entities.Session;
import com.springboot.security_app.SecurityApplication.entities.User;
import com.springboot.security_app.SecurityApplication.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    private final int SESSION_LIMIT = 2;

    public void generateNewSession(User user, String refreshToken) {

        List<Session> existingSessions = sessionRepository.findByUser(user);

        if (existingSessions.size() == SESSION_LIMIT) {
            existingSessions.sort(Comparator.comparing(Session::getLastUsedAt));

            Session leastRecentlyUsedSession = existingSessions.getFirst();

            sessionRepository.delete(leastRecentlyUsedSession);
        }

        Session newSession = Session.builder()
                .refreshToken(refreshToken)
                .user(user)
                .lastUsedAt(LocalDateTime.now())
                .build();

        sessionRepository.save(newSession);
    }

    public void validateSession(String refreshToken) {
        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException(
                        "Session not found for refresh token: " + refreshToken));

        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }
}
