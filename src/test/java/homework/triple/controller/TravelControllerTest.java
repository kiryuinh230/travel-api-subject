package homework.triple.controller;

import static homework.triple.controller.CityControllerTest.도시_등록;
import static homework.triple.controller.MemberControllerTest.로그인;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import homework.triple.acceptance.AcceptanceTest;
import homework.triple.controller.request.RegisterTravelRequest;
import homework.triple.domain.TravelState;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

class TravelControllerTest extends AcceptanceTest {

	private static final String TRAVEL_ENTRY_POINT = "/travel";

	@DisplayName("여행를 등록한다.")
	@Test
	void register_travel() {
		final String token = 로그인();
		final Long cityId = 도시_등록();

		final String travelName = "서울 여행";

		RegisterTravelRequest request = new RegisterTravelRequest(travelName, cityId);

		final ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header("Authorization", "Bearer " + token)
			.body(request)
			.when()
			.post(TRAVEL_ENTRY_POINT + "/register")
			.then().log().all()
			.extract();

		assertAll(
			() -> assertThat(response.jsonPath().getString("resultCode")).isEqualTo("success"),
			() -> assertThat(response.jsonPath().getString("result.id")).isNotEmpty(),
			() -> assertThat(response.jsonPath().getString("result.travelName")).isEqualTo(travelName),
			() -> assertThat(response.jsonPath().getString("result.state")).isEqualTo(TravelState.PLAN_TRIP.name()),
			() -> assertThat(response.jsonPath().getString("result.memberId")).isNotEmpty(),
			() -> assertThat(response.jsonPath().getString("result.cityId")).isNotEmpty()
		);
	}
}
