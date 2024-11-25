package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistStore;

import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    public static class AddMissionDto{

        @NotNull(message = "id는 null이 아니어야 함")
        @ExistStore
        Long storeId;
        @NotNull
        Integer reward;
        @NotBlank
        String missionSpec;
        @NotNull
        LocalDateTime deadLine;

    }



}
