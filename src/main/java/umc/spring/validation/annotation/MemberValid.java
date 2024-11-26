package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.MemberValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MemberValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MemberValid {

    String message() default "해당하는 사용자가 없습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
