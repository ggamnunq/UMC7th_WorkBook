package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.StoreValid;

@Component
@RequiredArgsConstructor
public class StoreValidator implements ConstraintValidator<StoreValid, Long> {

    private final StoreQueryService storeQueryService;

    @Override
    public void initialize(StoreValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {

        boolean isNull = storeId == null;
        //storeId null이 아니고 memberId에 해당하는 member가 있을 경우 true ( null값 먼저 검사 )
        boolean isValid = !isNull && storeQueryService.existsById(storeId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            if (isNull) {
                context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_REQUIRED.getMessage()).addConstraintViolation();
            }
            else{
                context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.getMessage()).addConstraintViolation();
            }
        }
        return isValid;

    }
}
