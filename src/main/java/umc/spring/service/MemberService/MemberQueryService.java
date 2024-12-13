package umc.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Review;
import umc.spring.domain.mapping.MemberMission;

public interface MemberQueryService {

    public boolean existsById(Long id);
    public Member findById(Long id);
    Page<Review> getReviewListByMemberId(Long memberId, Integer page);
    Page<MemberMission> getMissionListMyMemberId(Long memberId, Integer page, Boolean completed);

}
