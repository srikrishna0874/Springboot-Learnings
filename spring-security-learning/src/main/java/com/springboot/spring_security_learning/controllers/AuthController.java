package com.springboot.spring_security_learning.controllers;

import com.springboot.spring_security_learning.dto.LoginDto;
import com.springboot.spring_security_learning.dto.SignUpDto;
import com.springboot.spring_security_learning.dto.UserDto;
import com.springboot.spring_security_learning.services.AuthService;
import com.springboot.spring_security_learning.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final AuthService authService;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto) {
        System.out.println("Inside controller");
        UserDto userDto = userService.signUp(signUpDto);

        return ResponseEntity.ok(userDto);
    }

    @PostMapping(path = "/login")
    ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletRequest request,
                                 HttpServletResponse response) {
        String token = authService.login(loginDto);

        Cookie cookie = new Cookie("token", token);

        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        return ResponseEntity.ok().body(token);
    }

}
