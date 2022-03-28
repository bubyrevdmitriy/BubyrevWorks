package com.example.demo.validations;


import com.example.demo.annotation.PasswordMatches;
import com.example.demo.payload.request.ChangePasswordRequest;
import com.example.demo.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        if (obj instanceof SignupRequest) {
            SignupRequest userSignupRequest = (SignupRequest) obj;
            return userSignupRequest.getPassword().equals(userSignupRequest.getConfirmPassword());
        }
        if (obj instanceof ChangePasswordRequest) {
            ChangePasswordRequest changePasswordRequest = (ChangePasswordRequest) obj;
            return changePasswordRequest.getPassword().equals(changePasswordRequest.getConfirmPassword());
        }
        return false;
    }
}
