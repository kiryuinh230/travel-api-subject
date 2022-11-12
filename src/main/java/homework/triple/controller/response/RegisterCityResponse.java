package homework.triple.controller.response;

import homework.triple.domain.City;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterCityResponse {

	private Long id;
	private String cityName;
	private LocalDateTime lastInquiryDate;

	public static RegisterCityResponse fromCity(final City city) {
		return new RegisterCityResponse(city.getId(), city.getCityName(), city.getLastInquiryDate());
	}
}
