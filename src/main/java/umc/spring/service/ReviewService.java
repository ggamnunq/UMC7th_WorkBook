package umc.spring.service;

import umc.spring.domain.entity.Review;
import umc.spring.web.dto.ReviewAddDTO;

import java.util.List;

public interface ReviewService {

    List<Review> getReviews(Long storeId);

    void writeReview(ReviewAddDTO reviewAddDTO);

}
