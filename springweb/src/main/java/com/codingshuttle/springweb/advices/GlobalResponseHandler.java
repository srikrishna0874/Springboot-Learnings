package com.codingshuttle.springweb.advices;

import jakarta.annotation.Nonnull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@Nonnull MethodParameter returnType, @Nonnull Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, @Nonnull MethodParameter returnType,
                                  @Nonnull MediaType selectedContentType,
                                  @Nonnull Class selectedConverterType, @Nonnull ServerHttpRequest request, @Nonnull
                                  ServerHttpResponse response) {
        if (body instanceof ApiResponse) {
            return body;
        }

        return new ApiResponse<>(body);
    }
}
