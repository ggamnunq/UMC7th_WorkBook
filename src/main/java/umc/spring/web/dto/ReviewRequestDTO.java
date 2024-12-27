package umc.spring.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.MemberValid;
import umc.spring.validation.annotation.StoreValid;

public class ReviewRequestDTO {

    @Getter
    public static class AddDto {

        @Schema(description = "리뷰 제목", example = "Great Store!")
        @NotBlank
        String title;

        @Schema(description = "리뷰 내용", example = "The store was amazing.")
        @NotBlank
        String body;

        @Schema(description = "평점", example = "4.5")
        @NotNull
        Float score;

//        @StoreValid
//        Long storeId;
//        @MemberValid
//        Long memberId;

    }


}
