package homework.triple.controller.response;

import homework.triple.domain.City;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityResponse {

	private Long id;
	private String cityName;
	private LocalDateTime lastInquiryDate;

	public static CityResponse fromCity(final City city) {
		return new CityResponse(city.getId(), city.getCityName(), city.getLastInquiryDate());
	}
}
