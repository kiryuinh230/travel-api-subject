package homework.triple.domain.entity;

import homework.triple.domain.TravelState;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TravelEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private MemberEntity memberEntity;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private CityEntity cityEntity;


	@Enumerated(EnumType.STRING)
	private TravelState state;
}
