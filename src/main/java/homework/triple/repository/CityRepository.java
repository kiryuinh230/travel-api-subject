package homework.triple.repository;

import homework.triple.domain.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

	Boolean existsByCityName(String cityName);

}
