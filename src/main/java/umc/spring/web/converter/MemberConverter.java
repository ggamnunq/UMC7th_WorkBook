package umc.spring.web.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Review;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDto toJoinResultDto(Member member) {
        return MemberResponseDTO.JoinResultDto.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request) {

        Gender gender = null;
        switch(request.getGender()) {

            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .name(request.getName())
                .age(request.getAge())
                .gender(gender)
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .memberPreferList(new ArrayList<>())
                .build();

    }

    public static MemberResponseDTO.ReviewPreViewDto toReviewPreViewDto(Review review) {
        return MemberResponseDTO.ReviewPreViewDto.builder()
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .body(review.getBody())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static MemberResponseDTO.ReviewPreViewListDto toReviewPreViewListDto(Page<Review> reviewList) {

        List<MemberResponseDTO.ReviewPreViewDto> reviewPreViewDtoList = reviewList.stream().map(
                MemberConverter::toReviewPreViewDto
        ).toList();

        return MemberResponseDTO.ReviewPreViewListDto.builder()
                .reviewList(reviewPreViewDtoList)
                .listSize(reviewPreViewDtoList.size())
                .totalElements(reviewList.getTotalElements())
                .totalPage(reviewList.getTotalPages())
                .isFirst(reviewList.isFirst())
                .isLast(reviewList.isLast())
                .build();
    }

}
