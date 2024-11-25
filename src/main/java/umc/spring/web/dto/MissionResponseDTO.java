package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MissionAddResultDto {

        Long missionId;
        LocalDateTime createdAt;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MissionChallengeResultDto {
        List<Long> missionIds;
        Long memberId;
    }

}
