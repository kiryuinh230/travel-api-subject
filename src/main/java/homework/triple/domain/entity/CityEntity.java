package homework.triple.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "city")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cityName;

	private LocalDateTime lastInquiryDate;

	private CityEntity(final String cityName) {
		this.cityName = cityName;
	}

	public static CityEntity formCityName(final String cityName) {
		return new CityEntity(cityName);
	}

	public void update(final String updateCityName) {
		this.cityName = updateCityName;
	}
}
