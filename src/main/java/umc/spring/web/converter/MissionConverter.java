package umc.spring.web.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberResponseDTO;
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

    public static MissionResponseDTO.MissionDto toMissionDto(Mission mission) {
        return MissionResponseDTO.MissionDto.builder()
                .reward(mission.getReward())
                .deadLine(mission.getDeadLine())
                .missionSpec(mission.getMissionSpec())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static MissionResponseDTO.MissionListDto toMissionListDto(Page<MemberMission> memberMissions) {

        List<MissionResponseDTO.MissionDto> missionDtoList  = memberMissions.stream().map(
                memberMission -> toMissionDto(memberMission.getMission())
        ).toList();

        return MissionResponseDTO.MissionListDto.builder()
                .missionDtoList(missionDtoList)
                .listSize(memberMissions.getSize())
                .totalPage(memberMissions.getTotalPages())
                .totalElements(memberMissions.getTotalElements())
                .isFirst(memberMissions.isFirst())
                .isLast(memberMissions.isLast())
                .build();

    }

    public static MissionResponseDTO.CompleteMissionDto toCompleteMissionDto(MemberMission memberMission) {

        return MissionResponseDTO.CompleteMissionDto.builder()
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .build();
    }


}
