package umc.spring.repository.ReviewRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.entity.QReview;
import umc.spring.domain.entity.Review;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QReview review = QReview.review;

    @Override
    public List<Review> findReviewsByStore(Long storeId) {

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.store.id.eq(storeId));

        return jpaQueryFactory
                .selectFrom(review)
                .where(builder)
                .fetch();

    }
}
