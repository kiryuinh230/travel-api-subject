package homework.triple.domain.entity;

import homework.triple.domain.TravelState;
import homework.triple.domain.exception.CannotAccessTravelException;
import homework.triple.domain.exception.TravelDateException;
import homework.triple.global.error.ErrorCode;
import java.time.LocalDate;
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

	private LocalDate startDate;

	private LocalDate endDate;

	public TravelEntity(final String travelName, final TravelState state, final Long memberId, final Long cityId, final LocalDate startDate, final LocalDate endDate) {
		validateTravelDate(startDate, endDate);
		this.travelName = travelName;
		this.state = state;
		this.memberId = memberId;
		this.cityId = cityId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	private void validateTravelDate(final LocalDate startDate, final LocalDate endDate) {
		if (startDate.isAfter(endDate)) {
			throw new TravelDateException();
		}
	}

	public void validateWriter(final Long memberId) {
		if (!Objects.equals(this.memberId, memberId)) {
			throw new CannotAccessTravelException(ErrorCode.INVALID_PERMISSION);
		}
	}

	public void updateTravel(final Long writerId, final String updateName, final TravelState state, final LocalDate startDate, final LocalDate endDate) {
		validateWriter(writerId);
		validateTravelDate(startDate, endDate);
		this.travelName = updateName;
		this.state = state;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
