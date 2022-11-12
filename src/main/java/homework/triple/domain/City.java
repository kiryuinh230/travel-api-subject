package homework.triple.domain;

import homework.triple.domain.entity.CityEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class City {

	private Long id;
	private String cityName;
	private LocalDateTime lastInquiryDate;

	public static City fromEntity(CityEntity entity) {
		return new City(entity.getId(), entity.getCityName(), entity.getLastInquiryDate());
	}
}
