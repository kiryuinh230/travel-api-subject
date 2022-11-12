package homework.triple.repository;

import homework.triple.domain.entity.MemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findByUserName(String userName);
}
