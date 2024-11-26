package umc.spring.service.MissionService;

import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Store;

import java.util.List;

public interface MissionQueryService {

    boolean checkNotChallenging(List<Mission> missions, Member member);
    List<Mission> findMissionsByStore(Store store);

}
