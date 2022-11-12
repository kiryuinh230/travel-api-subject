package homework.triple.controller.response;

import homework.triple.domain.Travel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelResponse {

	private Long id;
	private String travelName;
	private String state;
	private Long memberId;
	private Long cityId;

	public static TravelResponse fromTravel(final Travel travel) {
		return new TravelResponse(travel.getId(), travel.getTravelName(), travel.getState(), travel.getMemberId(), travel.getCityId());
	}
}
