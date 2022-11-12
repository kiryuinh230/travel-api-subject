package homework.triple.controller;

import homework.triple.controller.request.RegisterCityRequest;
import homework.triple.controller.request.UpdateCityRequest;
import homework.triple.controller.response.CityResponse;
import homework.triple.controller.response.RegisterCityResponse;
import homework.triple.controller.response.UpdateCityResponse;
import homework.triple.domain.City;
import homework.triple.global.CommonResponse;
import homework.triple.service.CityService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CityController {

	private final CityService cityService;

	@PostMapping("/city/register")
	public CommonResponse<RegisterCityResponse> registerCity(@RequestBody @Valid final RegisterCityRequest request) {
		final City city = cityService.registerCity(request.getCityName());

		return CommonResponse.success(RegisterCityResponse.fromCity(city));
	}

	@PatchMapping("/city/{id}")
	public CommonResponse<UpdateCityResponse> updateCity(@RequestBody @Valid final UpdateCityRequest request, @PathVariable Long id) {
		final City city = cityService.updateCity(id, request.getUpdateCityName());

		return CommonResponse.success(UpdateCityResponse.fromCity(city));
	}

	@GetMapping("/city/{id}")
	public CommonResponse<CityResponse> findCityById(@PathVariable Long id) {
		final City city = cityService.findById(id);

		return CommonResponse.success(CityResponse.fromCity(city));
	}

	@DeleteMapping("/city/{id}")
	public CommonResponse<Void> deleteCity(@PathVariable Long id) {
		cityService.deleteCity(id);

		return CommonResponse.success();
	}
}
