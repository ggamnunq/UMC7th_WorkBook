package umc.spring.service;

import org.springframework.data.domain.Page;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.web.dto.MemberMissionDTO;
import umc.spring.web.dto.MissionDTO;

import java.util.List;

public interface MissionService {

    Page<MissionDTO.MissionStatusDTO> findMyMissionsByStatus(Long memberId, MissionStatus status, Integer page);

    List<Mission> findMissionsForHomePage(Long memberId, Long regionId, MissionStatus status, int offset);
    Page<MissionDTO.MissionRegionDTO> findMissionsForHomePage(Long memberId, Long regionId, MissionStatus status, Integer page);

    Integer getMissionCount(Long memberId, Long regionId);

}
