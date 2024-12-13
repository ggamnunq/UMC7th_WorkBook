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
import umc.spring.domain.entity.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.MemberValid;
import umc.spring.validation.annotation.MissionValid;
import umc.spring.validation.annotation.PageValid;
import umc.spring.web.converter.MemberConverter;
import umc.spring.web.converter.MissionConverter;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.MissionAddResultDto> addMission(@RequestBody @Valid MissionRequestDTO.AddMissionDto request) {
        Mission mission = missionCommandService.addMission(request);
        return ApiResponse.of(SuccessStatus.MISSION_ADD, MissionConverter.toResultDto(mission));
    }

    @PostMapping("/challenge")
    public ApiResponse<MissionResponseDTO.MissionChallengeResultDto> challengeMission(@RequestBody @Valid MissionRequestDTO.ChallengeMissionDto request) {
        List<Mission> missions = missionCommandService.challengeMission(request);
        return ApiResponse.of(SuccessStatus.MISSION_CHALLENGE, MissionConverter.toChallengeMissionResultDto(missions, request.getMemberId()));
    }

    @GetMapping("/")
    @Operation(summary = "내가 진행중/완료한 미션 목록 조회", description = "내가 진행중/완료한 미션을 조회하는 API이며, 페이징을 포함합니다. memberId, page 번호, 진행완료 여부를 Query String으로 주세요!!")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER202", description = "멤버가 도전중인 미션 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER203", description = "멤버가 완료한 미션 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE_LESS_THAN_ONE", description = "page 1보다 작음", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 ID, path variable입니다.")
    })
    public ApiResponse<MissionResponseDTO.MissionListDto> getMissionList(
            @MemberValid @RequestParam(name = "memberId") Long memberId, @PageValid @RequestParam(name = "page")Integer page, @RequestParam(name = "completed") Boolean completed){
        Page<MemberMission> memberMissions = memberQueryService.getMissionListMyMemberId(memberId, page, completed);
        if(completed){
            return ApiResponse.of(SuccessStatus.MEMBER_COMPLETED_MISSION, MissionConverter.toMissionListDto(memberMissions));
        }else{
            return ApiResponse.of(SuccessStatus.MEMBER_CHALLENGING_MISSION, MissionConverter.toMissionListDto(memberMissions));
        }
    }

    @PatchMapping("/complete")
    @Operation(summary = "진행중인 미션을 진행 완료로 바꾸기", description = "내가 진행중인 미션을 진행 완료로 바꾸는 API이며, 페이징을 포함합니다. qeury String으로 page 번호와 미션 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION202", description = "도전 중인 미션을 도전 완료로 변경"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER_MISSION_4001", description = "해당 미션을 멤버가 도전중이지 않음", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 ID, path variable입니다.")
    })
    public ApiResponse<MissionResponseDTO.CompleteMissionDto> completeMission(
            @MemberValid @RequestParam(name = "memberId") Long memberId, @MissionValid @RequestParam(name = "missionId") Long missionId) {

        MemberMission memberMission = memberCommandService.completeMission(memberId, missionId);
        return ApiResponse.of(SuccessStatus.MISSION_COMPLETE, MissionConverter.toCompleteMissionDto(memberMission));
    }

}
