package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.entity.Member;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.web.dto.MemberDTO;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberDTO.MyPageMemberDTO findMemberForMyPage(Long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        return MemberDTO.MyPageMemberDTO.builder()
                .name(member.getName())
                .email(member.getEmail())
                .point(member.getPoint())
                .build();
    }

}
