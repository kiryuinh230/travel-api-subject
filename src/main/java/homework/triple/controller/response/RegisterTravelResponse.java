package homework.triple.controller.response;

import homework.triple.domain.Travel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterTravelResponse {
	private Long id;
	private String travelName;
	private String state;
	private Long memberId;
	private Long cityId;

	public static RegisterTravelResponse fromTravel(final Travel travel) {
		return new RegisterTravelResponse(travel.getId(), travel.getTravelName(), travel.getState(), travel.getMemberId(), travel.getCityId());
	}
}
