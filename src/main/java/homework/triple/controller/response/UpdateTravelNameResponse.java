package homework.triple.controller.response;

import homework.triple.domain.Travel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTravelNameResponse {

	private Long id;
	private String updateTravelName;
	private String state;
	private Long memberId;
	private Long cityId;

	public static UpdateTravelNameResponse fromTravel(final Travel travel) {
		return new UpdateTravelNameResponse(travel.getId(), travel.getTravelName(), travel.getState(), travel.getMemberId(), travel.getCityId());
	}
}
