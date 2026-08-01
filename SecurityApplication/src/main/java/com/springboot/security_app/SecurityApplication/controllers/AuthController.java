package com.springboot.security_app.SecurityApplication.controllers;

import com.springboot.security_app.SecurityApplication.dto.LoginDto;
import com.springboot.security_app.SecurityApplication.dto.LoginResponseDto;
import com.springboot.security_app.SecurityApplication.dto.SignUpDto;
import com.springboot.security_app.SecurityApplication.dto.UserDto;
import com.springboot.security_app.SecurityApplication.services.AuthService;
import com.springboot.security_app.SecurityApplication.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final AuthService authService;

    @Value("deploy.env")
    private String deployEnvironment;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.signUp(signUpDto);

        return ResponseEntity.ok(userDto);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletRequest request,
                                                  HttpServletResponse response) {
        LoginResponseDto loginResponseDto = authService.login(loginDto);

        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnvironment));

        response.addCookie(cookie);

        return ResponseEntity.ok().body(loginResponseDto);
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("refreshToken not found inside cookies"));

        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);

        return ResponseEntity.ok().body(loginResponseDto);

    }

}
