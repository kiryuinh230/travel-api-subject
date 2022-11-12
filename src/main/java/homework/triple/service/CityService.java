package homework.triple.service;

import homework.triple.domain.City;
import homework.triple.domain.entity.CityEntity;
import homework.triple.domain.exception.DuplicateCityName;
import homework.triple.global.error.ErrorCode;
import homework.triple.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {

	private final CityRepository cityRepository;

	public City registerCity(final String cityName) {
		if (cityRepository.existsByCityName(cityName)) {
			throw new DuplicateCityName(ErrorCode.DUPLICATED_CITY_NAME);
		}

		final CityEntity cityEntity = cityRepository.save(CityEntity.formCityName(cityName));

		return City.fromEntity(cityEntity);
	}
}
