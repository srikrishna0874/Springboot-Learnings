package com.codingshuttle.springweb.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(
        validatedBy = PrimeNumberValidator.class
)
public @interface PrimeNumberValidation {
    String message() default "The number should be prime number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
