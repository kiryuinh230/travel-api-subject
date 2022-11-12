package homework.triple.service;

import homework.triple.domain.City;
import homework.triple.domain.TravelState;
import homework.triple.domain.entity.CityEntity;
import homework.triple.domain.exception.CannotDeleteCityException;
import homework.triple.domain.exception.CityNotFoundException;
import homework.triple.domain.exception.DuplicateCityName;
import homework.triple.global.error.ErrorCode;
import homework.triple.repository.CityRepository;
import homework.triple.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CityService {

	private final CityRepository cityRepository;
	private final TravelRepository travelRepository;

	@Transactional
	public City registerCity(final String cityName) {
		if (cityRepository.existsByCityName(cityName)) {
			throw new DuplicateCityName(ErrorCode.DUPLICATED_CITY_NAME);
		}

		final CityEntity cityEntity = cityRepository.save(CityEntity.formCityName(cityName));

		return City.fromEntity(cityEntity);
	}

	@Transactional
	public City updateCity(final Long id, final String updateCityName) {
		final CityEntity cityEntity = cityRepository.findById(id)
			.orElseThrow(() -> new CityNotFoundException(ErrorCode.CITY_NOT_FOUND));

		cityEntity.update(updateCityName);

		return City.fromEntity(cityEntity);
	}

	@Transactional(readOnly = true)
	public City findById(final Long id) {
		final CityEntity cityEntity = cityRepository.findById(id)
			.orElseThrow(() -> new CityNotFoundException(ErrorCode.CITY_NOT_FOUND));

		return City.fromEntity(cityEntity);
	}

	@Transactional
	public void deleteCity(final Long cityId) {
		if (travelRepository.existsByCityIdAndState(cityId, TravelState.TRAVELING)) {
		    throw new CannotDeleteCityException(ErrorCode.CANNOT_DELETE_CITY);
		}

		cityRepository.deleteById(cityId);
	}
}
