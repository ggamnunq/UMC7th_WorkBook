package umc.spring.config.security;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;
import umc.spring.domain.entity.Member;

import java.util.Map;

@Builder
@Getter
@ToString
public class OAuth2UserInfo {

    private String password;
    private String email;
    private String nickname;
    private String provider;

    public static OAuth2UserInfo of(String provider, Map<String, Object> attributes) {
        switch (provider) {
            case "google":
                return ofGoogle(attributes);
            case "kakao":
                return ofKakao(attributes);
            default:
                throw new RuntimeException();
        }
    }

    private static OAuth2UserInfo ofGoogle(Map<String, Object> attributes) {
        System.out.println(attributes.get("name"));
        return OAuth2UserInfo.builder()
                .password((String) attributes.get("sub"))
                .nickname((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .build();
    }

    private static OAuth2UserInfo ofKakao(Map<String, Object> attributes) {
        return OAuth2UserInfo.builder()
                .password(attributes.get("id").toString())
                .nickname((String) ((Map) attributes.get("properties")).get("nickname"))
                //이메일 정보는 지금 받아오지 않으니까 임시방편으로 해둠
                .email((String) ((Map) attributes.get("properties")).get("nickname") + "@kakao.com")
                .build();
    }

    public Member toEntity(){
        return Member.builder()
                .password(password)
                .email(email)
                .name(nickname)
                .build();
    }
}
