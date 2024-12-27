package umc.spring.repository.UuidRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.entity.Uuid;

@Repository
public interface UuidRepository extends JpaRepository<Uuid, Long> {

}
