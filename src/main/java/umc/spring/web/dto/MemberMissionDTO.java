package umc.spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.enums.MissionStatus;

@Builder
@Getter
public class MemberMissionDTO {

    private MissionStatus status;

    private Mission mission;

    private Member member;

    public MemberMissionDTO(MissionStatus status, Mission mission, Member member) {
        this.status = status;
        this.mission = mission;
        this.member = member;
    }

    @Override
    public String toString() {
        return "MemberMissionDTO{" +
                "status=" + status +
                ", mission=" + mission +
                ", member=" + member +
                '}';
    }
}
