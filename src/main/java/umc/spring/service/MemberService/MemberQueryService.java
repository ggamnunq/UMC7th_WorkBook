package umc.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Review;

public interface MemberQueryService {

    public boolean existsById(Long id);
    public Member findById(Long id);
    Page<Review> getReviewListByMemberId(Long memberId, Integer page);

}
