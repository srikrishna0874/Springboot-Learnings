package com.codingshuttle.springweb.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        if (password.length() < 10)
            return false;

        int upperCount = 0, lowerCount = 0, specialCharCount = 0;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upperCount++;
            }
            if (Character.isLowerCase(ch)) {
                lowerCount++;
            }
            if (!Character.isLetterOrDigit(ch)) {
                specialCharCount++;
            }
        }

        if (upperCount == 0)
            return false;
        if (lowerCount == 0)
            return false;
        return specialCharCount != 0;
    }


}
