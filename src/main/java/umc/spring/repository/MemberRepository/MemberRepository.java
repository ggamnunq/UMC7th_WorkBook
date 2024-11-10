package umc.spring.repository.MemberRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {


}
