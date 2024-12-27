package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.status.SuccessStatus;
import umc.spring.domain.entity.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.validation.annotation.MemberValid;
import umc.spring.validation.annotation.StoreValid;
import umc.spring.web.converter.ReviewConverter;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;
import umc.spring.web.dto.StoreRequestDTO;

import static org.eclipse.jdt.internal.compiler.parser.Parser.name;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping(
            value = "/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ApiResponse<ReviewResponseDTO.AddResultDto> add(
            @RequestPart("request") @Valid ReviewRequestDTO.AddDto request,
            @StoreValid @RequestParam Long storeId,
            @MemberValid @RequestParam Long memberId,
            @RequestPart("reviewPicture") MultipartFile reviewPicture
    ) {
        Review review = reviewCommandService.addReview(request, storeId, memberId, reviewPicture);
        return ApiResponse.of(SuccessStatus.REVIEW_ADD, ReviewConverter.toAddResultDto(review));
    }

}
