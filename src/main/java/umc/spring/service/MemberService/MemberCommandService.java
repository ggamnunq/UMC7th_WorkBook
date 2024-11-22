package umc.spring.service.MemberService;

import umc.spring.domain.entity.Member;
import umc.spring.web.dto.MemberRequestDTO;

public interface MemberCommandService {

    public Member joinMember(MemberRequestDTO.JoinDto request);

}
