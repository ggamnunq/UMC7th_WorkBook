package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.status.SuccessStatus;
import umc.spring.domain.entity.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.web.converter.ReviewConverter;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO.AddResultDto> add(@RequestBody @Valid ReviewRequestDTO.AddDto request) {
        Review review = reviewCommandService.addReview(request);
        return ApiResponse.of(SuccessStatus.REVIEW_ADD, ReviewConverter.toAddResultDto(review));
    }

}
