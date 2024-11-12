package umc.spring.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewDTO {

    @Getter
    @Builder
    public static class ReviewInquiryDTO{

        private String memberName;
        private Float score;
        private LocalDateTime createdAt;
        private String body;

        @Override
        public String toString() {
            return "ReviewInquiryDTO{" +
                    "memberName='" + memberName + '\'' +
                    ", score=" + score +
                    ", createdAt=" + createdAt +
                    ", body='" + body + '\'' +
                    '}';
        }
    }

    @Getter
    @Builder
    public static class ReviewWriteDTO{

        private String memberName;
        private String body;
        private Float score;

    }

}
