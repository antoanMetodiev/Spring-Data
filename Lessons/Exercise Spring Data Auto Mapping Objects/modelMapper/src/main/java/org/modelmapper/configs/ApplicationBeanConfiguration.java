package org.modelmapper.configs;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.modelmapper.util.ValidatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ValidatorService validatorService() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        return new ValidatorService() {
            @Override
            public <E> boolean isValid(E entity) {
                return validator.validate(entity).isEmpty();
            }

            @Override
            public <E> Set<ConstraintViolation<E>> validate(E entity) {
                return validator.validate(entity);
            }
        };
    }
}
