package umc.spring.service;

import umc.spring.web.dto.MemberDTO;


public interface MemberService {

    public MemberDTO.MyPageMemberDTO findMemberForMyPage(Long id);

}
