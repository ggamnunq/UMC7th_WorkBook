package umc.spring.web.dto;

import lombok.Getter;

@Getter
public class MemberAndStoreDTO {

    public Long memberId;
    public Long storeId;

    public MemberAndStoreDTO(Long memberId, Long storeId) {
        this.memberId = memberId;
        this.storeId = storeId;
    }
}
