package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Review;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.validation.annotation.MemberValid;
import umc.spring.validation.annotation.PageValid;
import umc.spring.validation.annotation.StoreValid;
import umc.spring.web.converter.MemberConverter;
import umc.spring.web.converter.StoreConverter;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDto> join(@RequestBody @Valid MemberRequestDTO.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDto(member));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "특정 멤버가 작성한 리뷰 목록 조회 API", description = "특정 멤버가 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. qeury String으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE_LESS_THAN_ONE", description = "page 1보다 작음", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH04", description = "access 토근 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH06", description = "access 토근 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 ID, path variable 입니다!")
    })
    public ApiResponse<MemberResponseDTO.ReviewPreViewListDto> getReviewListForMember(
            @MemberValid @PathVariable(name = "memberId") Long memberId, @PageValid @RequestParam(name = "page")Integer page){
        Page<Review> reviewList = memberQueryService.getReviewListByMemberId(memberId, page);
        return ApiResponse.onSuccess(MemberConverter.toReviewPreViewListDto(reviewList));
    }

}
