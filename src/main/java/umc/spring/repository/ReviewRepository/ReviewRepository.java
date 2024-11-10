package umc.spring.repository.ReviewRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {

}
