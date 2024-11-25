package umc.spring.web.converter;

import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Store;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

public class MissionConverter {

    public static MissionResponseDTO.MissionResultDto toResultDto(Mission mission) {
        return MissionResponseDTO.MissionResultDto.builder()
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

}
