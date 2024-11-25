package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;

public class ReviewRequestDTO {

    @Getter
    public static class AddDto {
        @ExistStore
        Long storeId;
        @ExistMember
        Long memberId;
        @NotBlank
        String body;
        @NotNull
        Float score;
    }


}
