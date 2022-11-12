package homework.triple.controller.response;

import homework.triple.domain.City;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCityResponse {

	private Long id;
	private String updateCityName;
	private LocalDateTime lastInquiryDate;

	public static UpdateCityResponse fromCity(final City city) {
		return new UpdateCityResponse(city.getId(), city.getCityName(), city.getLastInquiryDate());
	}

}
