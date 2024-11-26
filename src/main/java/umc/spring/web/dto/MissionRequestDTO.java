package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.MemberValid;
import umc.spring.validation.annotation.StoreValid;

import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    public static class AddMissionDto{

        @StoreValid
        Long storeId;
        @NotNull
        Integer reward;
        @NotBlank
        String missionSpec;
        @NotNull
        LocalDateTime deadLine;

    }

    @Getter
    public static class ChallengeMissionDto {

        @MemberValid
        Long memberId;
        @StoreValid
        Long storeId;

    }

}
