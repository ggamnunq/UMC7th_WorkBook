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
import umc.spring.apiPayload.code.status.SuccessStatus;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.validation.annotation.MemberValid;
import umc.spring.validation.annotation.PageValid;
import umc.spring.web.converter.MemberConverter;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

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
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER201", description = "멤버가 작성한 리뷰 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE_LESS_THAN_ONE", description = "page 1보다 작음", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 ID, path variable 입니다!")
    })
    public ApiResponse<MemberResponseDTO.ReviewPreViewListDto> getReviewList(
            @MemberValid @PathVariable(name = "memberId") Long memberId, @PageValid @RequestParam(name = "page")Integer page){
        Page<Review> reviewList = memberQueryService.getReviewListByMemberId(memberId, page);
        return ApiResponse.of(SuccessStatus.MEMBER_REVIEW, MemberConverter.toReviewPreViewListDto(reviewList));
    }

    @GetMapping("/{memberId}/missions")
    @Operation(summary = "내가 진행중/완료한 미션 목록 조회", description = "내가 진행중/완료한 미션을 조회하는 API이며, 페이징을 포함합니다. qeury String으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER202", description = "멤버가 도전중인 미션 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER203", description = "멤버가 완료한 미션 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE_LESS_THAN_ONE", description = "page 1보다 작음", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 ID, path variable입니다.")
    })
    public ApiResponse<MemberResponseDTO.MissionListDto> getMissionList(
            @MemberValid @PathVariable(name = "memberId") Long memberId, @PageValid @RequestParam(name = "page")Integer page, @RequestParam(name = "completed") Boolean completed){
        Page<MemberMission> memberMissions = memberQueryService.getMissionListMyMemberId(memberId, page, completed);
        if(completed){
            return ApiResponse.of(SuccessStatus.MEMBER_COMPLETED_MISSION, MemberConverter.toMissionListDto(memberMissions));
        }else{
            return ApiResponse.of(SuccessStatus.MEMBER_CHALLENGING_MISSION, MemberConverter.toMissionListDto(memberMissions));
        }
    }

}
