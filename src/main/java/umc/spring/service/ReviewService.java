package umc.spring.service;

import umc.spring.domain.entity.Review;
import umc.spring.web.dto.ReviewAddDTO;
import umc.spring.web.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO.ReviewInquiryDTO> getReviews(Long storeId);

    void writeReview(ReviewAddDTO reviewAddDTO);

}
