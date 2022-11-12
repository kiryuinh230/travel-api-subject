package homework.triple.controller.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterTravelRequest {

	private String travelName;
	private Long cityId;
	private LocalDate startDate;
	private LocalDate endDate;
}
