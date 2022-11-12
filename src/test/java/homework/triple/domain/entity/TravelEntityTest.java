package homework.triple.domain.entity;

import static org.assertj.core.api.Assertions.*;

import homework.triple.domain.TravelState;
import homework.triple.domain.exception.CannotAccessTravelException;
import homework.triple.domain.exception.TravelDateException;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TravelEntityTest {

	@DisplayName("여행을 등록하지 않은 유저가 수정할 경우 예외가 발생한다.")
	@Test
	void travel_update_not_writer() {
		String travelName = "서울여행";
		Long memberId = 1L;
		Long cityId = 1L;
		LocalDate startDate = LocalDate.of(22, 12, 1);
		LocalDate endDate = LocalDate.of(22, 12, 10);

		final TravelEntity travelEntity = new TravelEntity(travelName, TravelState.TRAVELING, memberId, cityId, startDate, endDate);

		Long otherMemberId = 2L;

		assertThatThrownBy(() -> travelEntity.validateWriter(otherMemberId))
			.isInstanceOf(CannotAccessTravelException.class);
	}

	@DisplayName("여행 생성시 여행 시작일이 종료일 이후라면 예외가 발생한다.")
	@Test
	void start_date_after_end_date() {
		String travelName = "서울여행";
		Long memberId = 1L;
		Long cityId = 1L;
		LocalDate startDate = LocalDate.of(22, 12, 10);
		LocalDate endDate = LocalDate.of(22, 12, 1);

		assertThatThrownBy(() -> new TravelEntity(travelName, TravelState.TRAVELING, memberId, cityId, startDate, endDate))
			.isInstanceOf(TravelDateException.class);
	}

	@DisplayName("여행 수정시 여행 시작일이 종료일 이후라면 예외가 발생한다.")
	@Test
	void travel_update_start_date_after_end_date() {
		String travelName = "서울여행";
		Long memberId = 1L;
		Long cityId = 1L;
		LocalDate startDate = LocalDate.of(22, 12, 1);
		LocalDate endDate = LocalDate.of(22, 12, 10);

		final TravelEntity travelEntity = new TravelEntity(travelName, TravelState.TRAVELING, memberId, cityId, startDate, endDate);

		LocalDate updateStartDate = LocalDate.of(22, 12, 20);
		LocalDate updateEndDate = LocalDate.of(22, 12, 10);


		assertThatThrownBy(() -> travelEntity.updateTravel(memberId, travelName, TravelState.TRAVELING, updateStartDate, updateEndDate))
			.isInstanceOf(TravelDateException.class);
	}
}
