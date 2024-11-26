package umc.spring.web.converter;

import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.List;

public class MissionConverter {

    public static MissionResponseDTO.MissionAddResultDto toResultDto(Mission mission) {
        return MissionResponseDTO.MissionAddResultDto.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.AddMissionDto request, Store store) {
        return Mission.builder()
                .reward(request.getReward())
                .missionSpec(request.getMissionSpec())
                .deadLine(request.getDeadLine())
                .store(store)
                .build();
    }

    public static MissionResponseDTO.MissionChallengeResultDto toChallengeMissionResultDto(List<Mission> missions, Long memberId) {

        List<Long> idList = missions.stream().map(Mission::getId).toList();

        return MissionResponseDTO.MissionChallengeResultDto.builder()
                .memberId(memberId)
                .missionIds(idList)
                .build();

    }

}
