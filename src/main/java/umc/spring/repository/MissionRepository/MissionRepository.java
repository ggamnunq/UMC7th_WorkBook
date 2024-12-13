package umc.spring.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Store;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {

    @Query("SELECT count(mm) FROM MemberMission mm" +
            " JOIN mm.mission m" +
            " JOIN m.store s" +
            " JOIN s.region r" +
            " WHERE mm.member.id = :memberId AND m.store.region.id = :regionId")
    Integer getCompleteMissionCount(@Param("memberId") Long memberId, @Param("regionId") Long regionId);

    List<Mission> findMissionsByStore(Store store);

    Page<Mission> findMissionsByStore(Store store, PageRequest pageRequest);

}
