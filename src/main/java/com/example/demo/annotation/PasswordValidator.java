package com.example.demo.annotation;

import jakarta.validation.Payload;
import jakarta.validation.Constraint;
import java.lang.annotation.*;

import com.example.demo.validation.PasswordStrengthValidator;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
	
	String message() default "Please choose a strong password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
