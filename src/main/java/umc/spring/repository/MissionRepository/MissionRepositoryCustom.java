package umc.spring.repository.MissionRepository;

import umc.spring.domain.entity.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MissionRepositoryCustom {

    List<Mission> findMissionsByMemberIdAndStatus(Long memberId, MissionStatus status, int offset);

    List<Mission> findMissionsByRegionAndMember(Long memberId, Long regionId, MissionStatus status, int offset);

}
