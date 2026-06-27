package com.springboot.spring_security_learning.services;

import com.springboot.spring_security_learning.dto.LoginDto;
import com.springboot.spring_security_learning.entities.Session;
import com.springboot.spring_security_learning.entities.User;
import com.springboot.spring_security_learning.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final SessionRepository sessionRepository;

    private final SessionService sessionService;

    private final UserService userService;

    @Transactional
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = jwtService.generateToken(user);

        Session existedSession = sessionService.hasActiveSession(user);

        if (existedSession != null) {
            existedSession.setToken(token);

        }
        else {

            sessionService.createSession(token, user);
        }

        return token;
    }
}
