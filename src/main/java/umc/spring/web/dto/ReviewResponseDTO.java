package umc.spring.web.dto;

import lombok.*;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddResultDto{

        Long reviewId;
        LocalDateTime createdAt;

    }

}
