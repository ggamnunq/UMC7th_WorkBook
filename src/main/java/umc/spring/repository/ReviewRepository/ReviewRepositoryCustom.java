package umc.spring.repository.ReviewRepository;

import umc.spring.domain.entity.Review;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> findReviewsByStore(Long storeId);

}
