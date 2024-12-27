package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.aws.s3.AmazonS3Manager;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Review;
import umc.spring.domain.entity.Store;
import umc.spring.domain.entity.Uuid;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.ReviewRepository.ReviewImageRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.repository.UuidRepository.UuidRepository;
import umc.spring.web.converter.ReviewConverter;
import umc.spring.web.dto.ReviewRequestDTO;

import java.util.UUID;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final UuidRepository uuidRepository;
    private final ReviewImageRepository reviewImageRepository;

    private final AmazonS3Manager s3Manager;

    @Override
    public Review addReview(ReviewRequestDTO.AddDto request, Long storeId, Long memberId, MultipartFile reviewPicture) {

        Review review = ReviewConverter.toReview(request);

        String uuid= UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(
                Uuid.builder()
                    .uuid(uuid).build()
        );

        String pictureURL = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), reviewPicture);

        review.setMember(memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND)));
        review.setStore(storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND)));

        reviewImageRepository.save(ReviewConverter.toReviewImage(pictureURL, review));
        return reviewRepository.save(review);
    }
}
