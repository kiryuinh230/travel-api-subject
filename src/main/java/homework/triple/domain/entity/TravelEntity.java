package homework.triple.domain.entity;

import homework.triple.domain.TravelState;
import homework.triple.domain.exception.CannotAccessTravelException;
import homework.triple.global.error.ErrorCode;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "travels")
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

	public TravelEntity(final String travelName, final TravelState state, final Long memberId, final Long cityId) {
		this.travelName = travelName;
		this.state = state;
		this.memberId = memberId;
		this.cityId = cityId;
	}

	public static TravelEntity planTrip(String travelName, Long memberId, Long cityId) {
		return new TravelEntity(travelName, TravelState.PLAN_TRIP, memberId, cityId);
	}

	public void validateWriter(final Long memberId) {
		if (!Objects.equals(this.memberId, memberId)) {
			throw new CannotAccessTravelException(ErrorCode.INVALID_PERMISSION);
		}
	}

	public void updateTravelName(final String updateName) {
		this.travelName = updateName;
	}
}
