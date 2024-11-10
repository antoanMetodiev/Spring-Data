package softuni.exam.util;

import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;

@Component
public class ValidationUtil_Impl implements ValidationUtil{

    private final Validator validator;

    public ValidationUtil_Impl() {
        this.validator = (Validator) Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <E> boolean isValid(E el) {
        System.out.println(this.validator.validate(el).isEmpty());
        System.out.println("--------------------------------------");
        return this.validator.validate(el).isEmpty();
    }
}
