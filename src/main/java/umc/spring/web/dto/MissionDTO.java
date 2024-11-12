package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;

public class MissionDTO {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MissionStatusDTO{
        private Integer reward;
        private String storeName;
        private String missionSpec;
        private MissionStatus status;

        @Override
        public String toString() {
            return "MissionStatusDTO{" +
                    "reward=" + reward +
                    ", storeName='" + storeName + '\'' +
                    ", missionSpec='" + missionSpec + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MissionRegionDTO{

        private Integer reward;
        private String regionName;
        private String missionSpec;
        private MissionStatus status;
        private LocalDateTime deadLine;

        @Override
        public String toString() {
            return "MissionRegionDTO{" +
                    "reward=" + reward +
                    ", regionName='" + regionName + '\'' +
                    ", missionSpec='" + missionSpec + '\'' +
                    ", status=" + status +
                    ", deadLine=" + deadLine +
                    '}';
        }
    }

}
