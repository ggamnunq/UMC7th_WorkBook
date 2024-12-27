package umc.spring.service.ReviewService;

import org.springframework.web.multipart.MultipartFile;
import umc.spring.domain.entity.Review;
import umc.spring.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {

    public Review addReview(ReviewRequestDTO.AddDto request, Long storeId, Long memberId, MultipartFile reviewPicture);

}
