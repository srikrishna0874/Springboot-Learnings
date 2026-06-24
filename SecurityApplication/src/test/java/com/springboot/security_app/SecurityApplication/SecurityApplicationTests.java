package com.springboot.security_app.SecurityApplication;

import com.springboot.security_app.SecurityApplication.entities.User;
import com.springboot.security_app.SecurityApplication.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

    @Autowired
    private JwtService jwtService;

    @Test
    void contextLoads() {

        User user = new User(1L, "sri@gmail.com", "1234");

        String token = jwtService.generateToken(user);

        System.out.println(token);

        Long id = jwtService.getUserIdFromToken(
                "eyJhbGciOiJIUzM4NCJ9" +
						".eyJzdWIiOiIxIiwiZW1haWwiOiJzcmlAZ21haWwuY29tIiwicm9sZXMiOlsiVVNFUiIsIkFETUlOIl0sImlhdCI6MTc4MTk3Nzk1NywiZXhwIjoxNzgxOTc4MDE3fQ.mLprjk8RrteQZcVM6saU2RnMvhCG6FPqgDznxet8tYDnJs5oeKD8ov-qH1XEO-9R");

        System.out.println(id);
    }

}
