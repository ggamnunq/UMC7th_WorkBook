package umc.spring.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Review;
import umc.spring.domain.entity.Store;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.ReviewAddDTO;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final EntityManager em;

    @Override
    public List<Review> getReviews(Long storeId) {

        List<Review> reviews = reviewRepository.findReviewsByStore(storeId);
        reviews.forEach(review -> {
            System.out.println("Review : " + review);
        });

        if (reviews.isEmpty()) {
            throw new RuntimeException("리뷰 없음");
        }

        return reviews;
    }

    @Override
    @Transactional(readOnly = false)
    public void writeReview(ReviewAddDTO reviewAddDTO) {

        Member member = memberRepository.findById(reviewAddDTO.getMemberId()).orElseThrow(RuntimeException::new);
        Store store = storeRepository.findById(reviewAddDTO.getStoreId()).orElseThrow(RuntimeException::new);

        Review review = Review.builder()
                .member(member)
                .store(store)
                .score(reviewAddDTO.getScore())
                .body(reviewAddDTO.getBody())
                .build();

        reviewRepository.save(review);
    }

}
