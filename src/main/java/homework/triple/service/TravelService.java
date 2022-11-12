package homework.triple.service;

import homework.triple.domain.Travel;
import homework.triple.domain.entity.TravelEntity;
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
}
