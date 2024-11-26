package umc.spring.service.ReviewService;

import umc.spring.domain.entity.Review;
import umc.spring.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {

    public Review addReview(ReviewRequestDTO.AddDto request);

}
