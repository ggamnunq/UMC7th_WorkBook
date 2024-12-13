package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.PageHandler;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Review;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionReopsitory.MemberMissionRepository;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean existsById(Long id) {
        return memberRepository.existsById(id);
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }

    @Override
    public Page<Review> getReviewListByMemberId(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Page<Review> reviews = reviewRepository.findAllByMember(member, PageRequest.of(page - 1, 10));
        // 페이지 값이 전체 페이지 수를 넘지 않는지 검사
        if (page > reviews.getTotalPages()) {
            throw new PageHandler(ErrorStatus.PAGE_OUT_OF_BOUND);
        }
        return reviews;
    }

    @Override
    public Page<MemberMission> getMissionListMyMemberId(Long memberId, Integer page, Boolean completed) {

        // member 찾음
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        // completed boolean 값에 따라 진행중/진행완료 구분
        MissionStatus status = completed ? MissionStatus.COMPLETE : MissionStatus.CHALLENGING;
        Page<MemberMission> memberMissions = memberMissionRepository.findByMemberAndStatus(member, status, PageRequest.of(page - 1, 10));

        if(page > memberMissions.getTotalPages()) {
            throw new PageHandler(ErrorStatus.PAGE_OUT_OF_BOUND);
        }
        return memberMissions;
    }

}
