package com.springboot.security_app.SecurityApplication.handlers;

import com.springboot.security_app.SecurityApplication.entities.User;
import com.springboot.security_app.SecurityApplication.services.JwtService;
import com.springboot.security_app.SecurityApplication.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;

    private final JwtService jwtService;

    @Value("deploy.env")
    private String deployEnvironment;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws
            IOException, ServletException {

        OAuth2AuthenticationToken oAuth2AuthenticationToken =
                (OAuth2AuthenticationToken) authentication; // Access Token
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) oAuth2AuthenticationToken.getPrincipal();

        String email = oAuth2User.getAttribute("email");

        User user = userService.getUserByEmail(email);

        if (user == null) {
            User newUser = User
                    .builder()
                    .email(email)
                    .build();

            user = userService.save(newUser);
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnvironment));

        response.addCookie(cookie);

        String frontendUrl = "http://localhost:8080/home.html?token=" + accessToken;

//        getRedirectStrategy().sendRedirect(request, response, frontendUrl);

        response.sendRedirect(frontendUrl);
    }

}
