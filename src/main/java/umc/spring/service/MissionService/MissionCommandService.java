package umc.spring.service.MissionService;

import umc.spring.domain.entity.Mission;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    public Mission addMission(MissionRequestDTO.AddMissionDto request);
}
