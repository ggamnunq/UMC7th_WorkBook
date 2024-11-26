package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.validation.annotation.MemberValid;

@Component
@RequiredArgsConstructor
public class MemberValidator implements ConstraintValidator<MemberValid, Long> {

    private final MemberQueryService memberQueryService;

    @Override
    public void initialize(MemberValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long memberId, ConstraintValidatorContext context) {

        boolean isNull = memberId == null;
        //memberId가 null이 아니고 memberId에 해당하는 member가 있을 경우 true ( null값 먼저 검사 )
        boolean isValid =!isNull && memberQueryService.existsById(memberId);

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            if (isNull) {
                context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_REQUIRED.toString()).addConstraintViolation();
            }else{
                context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString()).addConstraintViolation();
            }
        }
        return isValid;
    }
}
