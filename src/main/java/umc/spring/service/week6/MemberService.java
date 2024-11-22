package umc.spring.service.week6;

import umc.spring.web.dto.MemberDTO;


public interface MemberService {

    public MemberDTO.MyPageMemberDTO findMemberForMyPage(Long id);

}
