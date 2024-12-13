package umc.spring.web.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Review;
import umc.spring.domain.entity.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.util.List;

public class StoreConverter {

    public static StoreResponseDTO.AddResultDto toAddResultDto(Store store) {

        return StoreResponseDTO.AddResultDto.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }

    public static Store toStore(StoreRequestDTO.AddDto request) {

        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewDto toReviewPreViewDto(Review review) {
        return StoreResponseDTO.ReviewPreViewDto.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDto toReviewPreViewListDto(Page<Review> reviewList) {

        List<StoreResponseDTO.ReviewPreViewDto> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::toReviewPreViewDto).toList();

        return StoreResponseDTO.ReviewPreViewListDto.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static StoreResponseDTO.MissionDto toMissionDto(Mission mission) {
        return StoreResponseDTO.MissionDto.builder()
                .reward(mission.getReward())
                .deadLine(mission.getDeadLine())
                .missionSpec(mission.getMissionSpec())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static StoreResponseDTO.MissionListDto toMissionListDto(Page<Mission> missionList) {

        List<StoreResponseDTO.MissionDto> missionDtoList = missionList.stream()
                .map(StoreConverter::toMissionDto).toList();

        return StoreResponseDTO.MissionListDto.builder()
                .missionDtoList(missionDtoList)
                .listSize(missionList.getSize())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .build();
    }


}
