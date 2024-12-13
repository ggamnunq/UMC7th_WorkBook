package umc.spring.repository.MemberMissionReopsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsMemberMissionByMissionAndMember(Mission mission, Member member);
    Page<MemberMission> findByMemberAndStatus(Member member, MissionStatus status, PageRequest pageRequest);
}
