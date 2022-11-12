package homework.triple.service;

import homework.triple.domain.Travel;
import homework.triple.domain.entity.TravelEntity;
import homework.triple.domain.exception.TravelNotFoundEntityException;
import homework.triple.global.error.ErrorCode;
import homework.triple.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TravelService {

	private final TravelRepository travelRepository;

	@Transactional
	public Travel registerTravel(final Long memberId, final String travelName, final Long cityId) {
		final TravelEntity travelEntity = travelRepository.save(TravelEntity.planTrip(travelName, memberId, cityId));

		return Travel.fromEntity(travelEntity);
	}

	@Transactional
	public Travel updateTravelName(final Long memberId, final Long travelId, final String updateName) {
		final TravelEntity travelEntity = travelRepository.findById(travelId)
			.orElseThrow(() -> new TravelNotFoundEntityException(ErrorCode.TRAVEL_NOT_FOUNT));

		travelEntity.validateWriter(memberId);

		travelEntity.updateTravelName(updateName);

		return Travel.fromEntity(travelEntity);
	}
}
