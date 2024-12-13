package umc.spring.service.MemberService;

import umc.spring.domain.entity.Member;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberRequestDTO;

import java.util.List;

public interface MemberCommandService {

    public Member joinMember(MemberRequestDTO.JoinDto request);

    public MemberMission completeMission(Long memberId, Long missionId);

}
