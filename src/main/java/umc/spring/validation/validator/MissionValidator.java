package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.validation.annotation.MissionValid;

@Component
@RequiredArgsConstructor
public class MissionValidator implements ConstraintValidator<MissionValid, Long> {

    private final MissionQueryService missionQueryService;

    @Override
    public void initialize(MissionValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        boolean isValid = missionId != null && missionQueryService.existsById(missionId);
        System.out.println("isValid = " + isValid);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.getMessage()).addConstraintViolation();
        }
        return isValid;
    }
}
