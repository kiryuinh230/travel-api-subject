package homework.triple.service;

import homework.triple.domain.Travel;
import homework.triple.domain.TravelState;
import homework.triple.domain.entity.TravelEntity;
import homework.triple.domain.exception.TravelNotFoundEntityException;
import homework.triple.global.error.ErrorCode;
import homework.triple.repository.TravelRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TravelService {

	private final TravelRepository travelRepository;

	@Transactional
	public Travel registerTravel(final Long memberId, final String travelName, final Long cityId, final LocalDate startDate, final LocalDate endDate) {
		final TravelEntity travelEntity = travelRepository.save(new TravelEntity(travelName,  TravelState.PLAN_TRIP, memberId, cityId, startDate, endDate));

		return Travel.fromEntity(travelEntity);
	}

	@Transactional
	public Travel updateTravel(final Long memberId, final Long travelId, final String updateName, final TravelState state, final LocalDate startDate, final LocalDate endDate) {
		final TravelEntity travelEntity = travelRepository.findById(travelId)
			.orElseThrow(() -> new TravelNotFoundEntityException(ErrorCode.TRAVEL_NOT_FOUNT));

		travelEntity.updateTravel(memberId, updateName, state, startDate, endDate);

		return Travel.fromEntity(travelEntity);
	}

	@Transactional
	public void deleteTravel(final Long memberId, final Long travelId) {
		final TravelEntity travelEntity = travelRepository.findById(travelId)
			.orElseThrow(() -> new TravelNotFoundEntityException(ErrorCode.TRAVEL_NOT_FOUNT));

		travelEntity.validateWriter(memberId);

		travelRepository.delete(travelEntity);
	}

	@Transactional(readOnly = true)
	public Travel findById(final Long memberId, final Long travelId) {
		final TravelEntity travelEntity = travelRepository.findById(travelId)
			.orElseThrow(() -> new TravelNotFoundEntityException(ErrorCode.TRAVEL_NOT_FOUNT));

		travelEntity.validateWriter(memberId);

		return Travel.fromEntity(travelEntity);
	}
}
