package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.web.dto.MissionDTO;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    
    private final Integer pageSize = 3;

    @Override
    public Page<MissionDTO.MissionStatusDTO> findMyMissionsByStatus(Long memberId, MissionStatus status, Integer page) {

        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Mission> pagedMissions = missionRepository.findMissionsByMemberIdAndStatus(memberId, status, pageRequest);
        return pagedMissions.map(mission ->

                MissionDTO.MissionStatusDTO.builder()
                        .missionSpec(mission.getMissionSpec())
                        .reward(mission.getReward())
                        .storeName(mission.getStore().getName())
                        .status(status)
                        .build()

        );
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
    public Page<MissionDTO.MissionRegionDTO> findMissionsForHomePage(Long memberId, Long regionId, MissionStatus status, Integer page) {

        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Mission> pagedMissions = missionRepository.findMissionsByRegionAndMember(memberId, regionId, status, pageRequest);

        return pagedMissions.map(mission ->
                MissionDTO.MissionRegionDTO.builder()
                        .reward(mission.getReward())
                        .regionName(mission.getStore().getRegion().getName())
                        .missionSpec(mission.getMissionSpec())
                        .status(status)
                        .deadLine(mission.getDeadLine())
                        .build()
        );
    }

    @Override
    public Integer getMissionCount(Long memberId, Long regionId) {

        return missionRepository.getCompleteMissionCount(memberId, regionId);

    }

}
