package umc.spring.service;

import umc.spring.domain.entity.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MissionService {

    List<Mission> findMyMissionsByStatus(Long memberId, MissionStatus status, int offset);

    List<Mission> findMissionsForHomePage(Long memberId, Long regionId, MissionStatus status, int offset);

    Integer getMissionCount(Long memberId, Long regionId);

}
