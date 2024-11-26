package umc.spring.service.MemberService;

import umc.spring.domain.entity.Member;

public interface MemberQueryService {

    public boolean existsById(Long id);
    public Member findById(Long id);

}
