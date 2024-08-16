package com.buihuuduy.identity.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DOBValidator implements ConstraintValidator<DOBConstraint, LocalDate>
{

    private int min;

    @Override
    public void initialize(DOBConstraint constraintAnnotation) {
        min = constraintAnnotation.min();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext)
    {
        int year = LocalDate.now().getYear() - localDate.getYear();
        return year >= min;
    }
}
