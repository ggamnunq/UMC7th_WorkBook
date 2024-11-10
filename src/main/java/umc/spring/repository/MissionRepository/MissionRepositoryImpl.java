package umc.spring.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.QMemberMission;

import java.util.List;

import static umc.spring.domain.entity.QMission.mission;
import static umc.spring.domain.entity.QRegion.region;
import static umc.spring.domain.entity.QStore.store;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMemberMission memberMission = QMemberMission.memberMission;

    @Override
    public List<Mission> findMissionsByMemberIdAndStatus(Long memberId , MissionStatus status, int offset) {

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(memberMission.member.id.eq(memberId));
        builder.and(memberMission.status.eq(status));

        List<MemberMission> memberMissionList = jpaQueryFactory
                .selectFrom(memberMission)
                .where(builder)
                .offset(offset)
                .limit(3)
                .fetch();

        return memberMissionList.stream().map(MemberMission::getMission).toList();
    }

    @Override
    public List<Mission> findMissionsByRegionAndMember(Long memberId, Long regionId, MissionStatus status, int offset) {

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(memberMission.member.id.eq(memberId));
        builder.and(memberMission.status.eq(status));

        List<MemberMission> memberMissionList = jpaQueryFactory
                .selectFrom(memberMission)
                .join(memberMission.mission, mission).fetchJoin()
                .join(mission.store, store).fetchJoin()
                .join(store.region, region).fetchJoin()
                .where(builder, region.id.eq(regionId))
                .offset(offset)
                .limit(3)
                .fetch();

        return memberMissionList.stream().map(MemberMission::getMission).toList();

    }


}