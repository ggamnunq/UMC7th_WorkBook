package umc.spring.web.converter;

import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Review;
import umc.spring.domain.entity.ReviewImage;
import umc.spring.domain.entity.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

public class ReviewConverter {

    public static ReviewResponseDTO.AddResultDto toAddResultDto(Review review) {
        return ReviewResponseDTO.AddResultDto.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.AddDto request) {
        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .build();
    }

    public static ReviewImage toReviewImage(String pictureURL, Review review) {
        return ReviewImage.builder()
                .review(review)
                .url(pictureURL)
                .build();
    }
}
