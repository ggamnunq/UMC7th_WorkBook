package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.status.SuccessStatus;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.web.converter.MissionConverter;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionRestController {

    public final MissionCommandService missionCommandService;

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

}
