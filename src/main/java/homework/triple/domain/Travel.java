package homework.triple.domain;

import homework.triple.domain.entity.TravelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Travel {

	private Long id;
	private String travelName;
	private String state;
	private Long memberId;
	private Long cityId;

	public static Travel fromEntity(final TravelEntity entity) {
		return new Travel(entity.getId(), entity.getTravelName(), entity.getState().name(), entity.getMemberId(), entity.getCityId());
	}
}
