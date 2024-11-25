package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.status.SuccessStatus;
import umc.spring.domain.entity.Mission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.web.converter.MissionConverter;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionRestController {

    public final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.MissionResultDto> addMission(@RequestBody @Valid MissionRequestDTO.AddMissionDto request) {
        Mission mission = missionCommandService.addMission(request);
        return ApiResponse.of(SuccessStatus.MISSION_ADD, MissionConverter.toResultDto(mission));
    }

}
