package homework.triple.repository;

import homework.triple.domain.entity.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<TravelEntity, Long> {

}
