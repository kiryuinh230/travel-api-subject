package homework.triple.controller;

import homework.triple.controller.request.RegisterTravelRequest;
import homework.triple.controller.request.UpdateTravelRequest;
import homework.triple.controller.response.RegisterTravelResponse;
import homework.triple.controller.response.TravelResponse;
import homework.triple.controller.response.UpdateTravelNameResponse;
import homework.triple.domain.Member;
import homework.triple.domain.Travel;
import homework.triple.global.CommonResponse;
import homework.triple.service.TravelService;
import homework.triple.utils.ClassUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TravelController {

	private final TravelService travelService;

	@GetMapping("/travel/{travelId}")
	public CommonResponse<TravelResponse> findTravelById(Authentication authentication, @PathVariable Long travelId) {
		Member member = ClassUtils.getSafeCastInstance(authentication.getPrincipal(), Member.class);

		Travel travel = travelService.findById(member.getId(), travelId);

		return CommonResponse.success(TravelResponse.fromTravel(travel));
	}

	@PostMapping("/travel/register")
	public CommonResponse<RegisterTravelResponse> registerTravel(Authentication authentication, @RequestBody RegisterTravelRequest request) {
		Member member = ClassUtils.getSafeCastInstance(authentication.getPrincipal(), Member.class);

		final Travel travel = travelService.registerTravel(member.getId(), request.getTravelName(), request.getCityId(), request.getStartDate(), request.getEndDate());

		return CommonResponse.success(RegisterTravelResponse.fromTravel(travel));
	}

	@PatchMapping("/travel/{travelId}")
	public CommonResponse<UpdateTravelNameResponse> updateTravel(Authentication authentication, @RequestBody UpdateTravelRequest request, @PathVariable Long travelId) {
		Member member = ClassUtils.getSafeCastInstance(authentication.getPrincipal(), Member.class);

		final Travel travel = travelService.updateTravel(member.getId(), travelId, request.getUpdateName(), request.getUpdateState(), request.getStartDate(), request.getEndDate());

		return CommonResponse.success(UpdateTravelNameResponse.fromTravel(travel));
	}

	@DeleteMapping("/travel/{travelId}")
	public CommonResponse<Void> deleteTravel(Authentication authentication, @PathVariable Long travelId) {
		Member member = ClassUtils.getSafeCastInstance(authentication.getPrincipal(), Member.class);

		travelService.deleteTravel(member.getId(), travelId);

		return CommonResponse.success();
	}
}
