package homework.triple.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterTravelRequest {

	private String travelName;
	private Long cityId;
}
