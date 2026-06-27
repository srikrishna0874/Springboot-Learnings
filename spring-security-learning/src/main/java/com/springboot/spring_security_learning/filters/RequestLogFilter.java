package com.springboot.spring_security_learning.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RequestLogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        logger.info(request.getRequestURI());
        logger.info(request.getMethod());
        logger.info(response.getStatus());
        logger.info(request.getRemoteAddr());
        logger.info(request.getRemoteHost());
        logger.info(request.getRemoteUser());
        logger.info(request.getHeader("User-Agent"));

        filterChain.doFilter(request, response);

    }
}
