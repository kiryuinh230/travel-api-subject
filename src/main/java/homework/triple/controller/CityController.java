package homework.triple.controller;

import homework.triple.controller.request.RegisterCityRequest;
import homework.triple.controller.response.RegisterCityResponse;
import homework.triple.domain.City;
import homework.triple.global.CommonResponse;
import homework.triple.service.CityService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CityController {

	private final CityService cityService;

	@PostMapping("/city/register")
	public CommonResponse registerCity(@RequestBody @Valid final RegisterCityRequest request) {
		final City city = cityService.registerCity(request.getCityName());

		return CommonResponse.success(RegisterCityResponse.fromCity(city));
	}
}
