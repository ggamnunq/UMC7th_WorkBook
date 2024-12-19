package umc.spring.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import umc.spring.domain.entity.Member;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.Role;
import umc.spring.repository.MemberRepository.MemberRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 로그인 진행 중인 서비스를 구분
        // 네이버로 로그인 진행 중인지, 구글로 로그인 진행 중인지, ... 등을 구분
        // ex) "naver", "google", "kakao"
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // 필요한 정보를 provider( 구글, 카카오 )에 따라 다르게 mapping
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(registrationId, oAuth2User.getAttributes());

        // 멤버 업데이트 또는 저장
        saveOrUpdateUser(oAuth2UserInfo.getEmail(), oAuth2UserInfo.getNickname());

        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(),
                oAuth2User.getAttributes(),
                "email"
        );

//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
//
//        String nickname = (String) properties.get("nickname");
//        String email = nickname + "@kakao.com";
//
//        Member member = saveOrUpdateUser(nickname, email);
//
//        // attributes 수정 -> 이메일을 주요 식별자로 설정하기 위해
//        HashMap<String, Object> modifiedAttributes = new HashMap<>(attributes);
//        modifiedAttributes.put("email", email);
//
//        return new DefaultOAuth2User(
//                oAuth2User.getAuthorities(),
//                modifiedAttributes,
//                "email" // email을 주요 식별자로 설정
//        );
    }

    private Member saveOrUpdateUser(String email, String nickname) {
        Member member = memberRepository.findByEmail(email)
                .orElse(Member.builder()
                        .email(email)
                        .name(nickname)
                        .password(passwordEncoder.encode("OAUTH_USER_" + UUID.randomUUID()))
                        .gender(Gender.NONE)
                        .address("소셜 로그인")
                        .specAddress("소셜 로그인")
                        .role(Role.USER)
                        .build());

        return memberRepository.save(member);
    }

}
