package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Store;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.AlreadyChallenging;
import umc.spring.web.dto.MissionRequestDTO;

import java.util.List;


@Component
@RequiredArgsConstructor
public class AlreadyChallengingValidator implements ConstraintValidator<AlreadyChallenging, MissionRequestDTO.ChallengeMissionDto> {

    private final MissionQueryService missionQueryService;
    private final StoreQueryService storeQueryService;
    private final MemberQueryService memberQueryService;

    @Override
    public boolean isValid(MissionRequestDTO.ChallengeMissionDto missionDto, ConstraintValidatorContext context) {

        if(missionDto.getStoreId() == null ) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.getMessage()).addConstraintViolation();
            return false;
        }
        if(missionDto.getMemberId() == null ) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.getMessage()).addConstraintViolation();
            return false;
        }

        // 기존 검증 로직
        Member member = memberQueryService.findById(missionDto.getMemberId());
        Store store = storeQueryService.findById(missionDto.getStoreId());
        List<Mission> missions = missionQueryService.findMissionsByStore(store);

        //도전중인 미션이 있는지 검사.
        boolean isValid = missionQueryService.checkNotChallenging(missions, member);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.ALREADY_CHALLENGING.getMessage()).addConstraintViolation();
        }

        return isValid;
    }

}
