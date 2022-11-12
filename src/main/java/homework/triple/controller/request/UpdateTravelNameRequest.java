package homework.triple.controller.request;

import homework.triple.domain.TravelState;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTravelNameRequest {

	private String updateName;
	private TravelState updateState;
	private LocalDate startDate;
	private LocalDate endDate;

}
