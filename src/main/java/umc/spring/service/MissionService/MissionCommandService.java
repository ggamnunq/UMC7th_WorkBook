package umc.spring.service.MissionService;

import umc.spring.domain.entity.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;

import java.util.List;

public interface MissionCommandService {
    public Mission addMission(MissionRequestDTO.AddMissionDto request);
    public List<Mission> challengeMission(MissionRequestDTO.ChallengeMissionDto request);
}
