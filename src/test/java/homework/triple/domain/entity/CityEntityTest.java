package homework.triple.domain.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CityEntityTest {

	@DisplayName("도시 수정 테스트")
	@Test
	void update_city() {
		CityEntity city = CityEntity.formCityName("서울");

		final String updateName = "판교";

		city.update(updateName);

		assertThat(city.getCityName()).isEqualTo(updateName);
	}
}
