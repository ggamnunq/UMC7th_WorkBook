package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.PageHandler;
import umc.spring.validation.annotation.PageValid;

@Component
@RequiredArgsConstructor
public class PageValidator implements ConstraintValidator<PageValid, Integer> {

    @Override
    public void initialize(PageValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {

        // 1 보다 작을 때
        if (page < 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_LESS_THAN_ONE.getMessage()).addConstraintViolation();
            throw new PageHandler(ErrorStatus.PAGE_LESS_THAN_ONE);
        }

        return true;
    }
}
