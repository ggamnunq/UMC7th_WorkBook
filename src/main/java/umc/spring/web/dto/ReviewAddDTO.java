package umc.spring.web.dto;

import lombok.Getter;

@Getter
public class ReviewAddDTO {

    private Long memberId;
    private Long storeId;
    private Float score;
    private String body;

    public ReviewAddDTO(Long memberId, Long storeId, Float score, String body) {
        this.memberId = memberId;
        this.storeId = storeId;
        this.score = score;
        this.body = body;
    }

    @Override
    public String toString() {
        return "ReviewAddDTO{" +
                "memberId=" + memberId +
                ", storeId=" + storeId +
                ", score=" + score +
                ", body='" + body + '\'' +
                '}';
    }
}