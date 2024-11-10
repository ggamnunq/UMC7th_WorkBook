package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MissionRepository.MissionRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;

    @Override
    public List<Mission> findMyMissionsByStatus(Long memberId, MissionStatus status, int offset) {

        List<Mission> missions = missionRepository.findMissionsByMemberIdAndStatus(memberId, status, offset);
        missions.forEach(mission -> {
            System.out.println("Mission: " + mission);
        });

        if (missions.isEmpty()) {
            throw new RuntimeException("결과 없음");
        }

        return missions;

    }

    @Override
    public List<Mission> findMissionsForHomePage(Long memberId, Long regionId, MissionStatus status, int offset) {

        List<Mission> missions = missionRepository.findMissionsByRegionAndMember(memberId, regionId, status, offset);

        if(missions.isEmpty()) {
            throw new RuntimeException("해당 지역에 도전중인 미션 없음");
        }

        return missions;

    }

    @Override
    public Integer getMissionCount(Long memberId, Long regionId) {

        return missionRepository.getCompleteMissionCount(memberId, regionId);

    }

}
