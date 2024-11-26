package umc.spring.repository.MemberMissionReopsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsMemberMissionByMissionAndMember(Mission mission, Member member);
}
