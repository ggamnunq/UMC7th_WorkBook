package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.StoreValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StoreValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StoreValid {

    String message() default "해당하는 가게가 없습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
