package homework.triple.domain.entity;

import homework.triple.domain.TravelState;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String travelName;

	@Enumerated(EnumType.STRING)
	private TravelState state;

	private Long memberId;

	private Long cityId;

	private TravelEntity(final String travelName, final TravelState state, final Long memberId, final Long cityId) {
		this.travelName = travelName;
		this.state = state;
		this.memberId = memberId;
		this.cityId = cityId;
	}

	public static TravelEntity planTrip(String travelName, Long memberId, Long cityId) {
		return new TravelEntity(travelName, TravelState.PLAN_TRIP, memberId, cityId);
	}

}
