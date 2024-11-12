package umc.spring.web.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberDTO {

    @Getter
    @Builder
    public static class MyPageMemberDTO{

        //이름 , 이메일, 휴대전화, 인증여부, 포인
        private String name;
        private String email;
        private Integer point;

        @Override
        public String toString() {
            return "MyPageMemberDTO{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", point=" + point +
                    '}';
        }
    }
}
