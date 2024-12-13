package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Store;
import umc.spring.repository.MemberMissionReopsitory.MemberMissionRepository;
import umc.spring.repository.MissionRepository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    public boolean existsById(Long id) {
        return missionRepository.existsById(id);
    }


    @Override
    public boolean checkNotChallenging(List<Mission> missions, Member member) {

        //같은 mission과 member를 가진 MemberMission이 없어야 true -> 도전중이거나 이미 수행한 미션이 아니라는 의미
        return missions.stream().noneMatch(mission ->
                memberMissionRepository.existsMemberMissionByMissionAndMember(mission, member)
        );
    }

    @Override
    public List<Mission> findMissionsByStore(Store store) {
        return missionRepository.findMissionsByStore(store);
    }
}
