package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.MemberValid;
import umc.spring.validation.annotation.StoreValid;

public class ReviewRequestDTO {

    @Getter
    public static class AddDto {
        @StoreValid
        Long storeId;
        @MemberValid
        Long memberId;
        @NotBlank
        String body;
        @NotNull
        Float score;
    }


}
