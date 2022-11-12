package homework.triple.controller;

import homework.triple.controller.request.RegisterTravelRequest;
import homework.triple.controller.response.RegisterTravelResponse;
import homework.triple.domain.Member;
import homework.triple.domain.Travel;
import homework.triple.global.CommonResponse;
import homework.triple.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TravelController {

	private final TravelService travelService;

	@PostMapping("/travel/register")
	public CommonResponse<RegisterTravelResponse> registerTravel(Authentication authentication, @RequestBody RegisterTravelRequest request) {
		final Member loginMember = (Member) authentication.getPrincipal();

		final Travel travel = travelService.registerTravel(loginMember.getId(), request.getTravelName(), request.getCityId());

		return CommonResponse.success(RegisterTravelResponse.fromTravel(travel));
	}

}
