package umc.spring.service.week6;

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
import umc.spring.web.dto.ReviewDTO;

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
    public List<ReviewDTO.ReviewInquiryDTO> getReviews(Long storeId) {

        List<Review> reviews = reviewRepository.findReviewsByStore(storeId);

        List<ReviewDTO.ReviewInquiryDTO> dtoList = reviews.stream().map(review ->
                ReviewDTO.ReviewInquiryDTO.builder()
                        .memberName(review.getMember().getName())
                        .score(review.getScore())
                        .createdAt(review.getCreatedAt())
                        .body(review.getBody())
                        .build()
        ).toList();

        if (dtoList.isEmpty()) {
            throw new RuntimeException("리뷰 없음");
        }

        return dtoList;
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