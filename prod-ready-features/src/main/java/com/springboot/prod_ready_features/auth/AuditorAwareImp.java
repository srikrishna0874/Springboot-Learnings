package com.springboot.prod_ready_features.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImp implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //get security context
        //get authentication
        //get principle
        //get username
        return Optional.of("Sri Krishna");
    }
}
