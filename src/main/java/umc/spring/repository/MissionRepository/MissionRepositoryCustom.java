package umc.spring.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MissionRepositoryCustom {

    Page<Mission> findMissionsByMemberIdAndStatus(Long memberId, MissionStatus status, Pageable pageable);

    List<Mission> findMissionsByRegionAndMember(Long memberId, Long regionId, MissionStatus status, int offset);
    Page<Mission> findMissionsByRegionAndMember(Long memberId, Long regionId, MissionStatus status, Pageable pageable);


}
