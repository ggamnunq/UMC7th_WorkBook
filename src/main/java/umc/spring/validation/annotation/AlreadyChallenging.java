package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.AlreadyChallengingValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AlreadyChallengingValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AlreadyChallenging {

    String message() default "이미 도전중입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
