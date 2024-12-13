package umc.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseCode;
import umc.spring.apiPayload.code.ReasonDTO;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    //일반적 응답
    _OK(HttpStatus.OK, "COMMON200", "성공입니다."),

    //멤버 응답
    MEMBER_REVIEW(HttpStatus.OK, "MEMBER201", "멤버가 작성한 리뷰 조회 성공"),

    //가게 응답
    STORE_ADD(HttpStatus.OK, "STORE200", "가게 추가 성공"),
    STORE_REVIEW(HttpStatus.OK, "STORE201", "가게 리뷰 조회 성공"),
    STORE_MISSION(HttpStatus.OK, "STORE202", "가게의 미션 목록 조회 성공"),


    //리뷰 응답
    REVIEW_ADD(HttpStatus.OK, "REVIEW200", "리뷰 추가 성공"),

    //미션 응답
    MISSION_ADD(HttpStatus.OK, "MISSION200", "가게에 미션 추가 성공"),
    MISSION_CHALLENGE(HttpStatus.OK, "MISSION201", "가게의 미션을 도전 중인 미션에 추가 성공")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
