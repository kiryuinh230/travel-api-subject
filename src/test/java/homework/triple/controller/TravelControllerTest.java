package homework.triple.controller;

import static homework.triple.controller.CityControllerTest.도시_등록;
import static homework.triple.controller.MemberControllerTest.로그인;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import homework.triple.acceptance.AcceptanceTest;
import homework.triple.controller.request.RegisterTravelRequest;
import homework.triple.controller.request.UpdateTravelRequest;
import homework.triple.domain.TravelState;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class TravelControllerTest extends AcceptanceTest {

	private static final String TRAVEL_ENTRY_POINT = "/travel";

	@DisplayName("여행를 등록한다.")
	@Test
	void register_travel() {
		final String token = 로그인();
		final Long cityId = 도시_등록();

		final String travelName = "서울 여행";

		final RegisterTravelRequest request = new RegisterTravelRequest(travelName, cityId, LocalDate.of(22, 10, 1), LocalDate.of(22, 12, 1));

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

	@DisplayName("여행을 수정한다.")
	@Test
	void update_travel() {
		final Long cityId = 도시_등록();
		final Long travelId = 여행_등록(cityId);

		final String token = 로그인();

		final String updateTravelName = "일본 여행";
		final TravelState updateTravelState = TravelState.TRAVELING;
		final LocalDate startDate = LocalDate.of(22, 12, 1);
		final LocalDate endDate = LocalDate.of(22, 12, 10);

		final UpdateTravelRequest request = new UpdateTravelRequest(updateTravelName, updateTravelState, startDate, endDate);

		final ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header("Authorization", "Bearer " + token)
			.body(request)
			.when()
			.patch(TRAVEL_ENTRY_POINT + "/" + travelId)
			.then().log().all()
			.extract();

		assertAll(
			() -> assertThat(response.jsonPath().getString("resultCode")).isEqualTo("success"),
			() -> assertThat(response.jsonPath().getString("result.id")).isNotEmpty(),
			() -> assertThat(response.jsonPath().getString("result.updateTravelName")).isEqualTo(updateTravelName),
			() -> assertThat(response.jsonPath().getString("result.memberId")).isNotEmpty(),
			() -> assertThat(response.jsonPath().getString("result.cityId")).isNotEmpty()
		);
	}

	@DisplayName("여행을 삭제한다.")
	@Test
	void delete_travel() {
		final Long cityId = 도시_등록();
		final Long travelId = 여행_등록(cityId);

		final String token = 로그인();

		final ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header("Authorization", "Bearer " + token)
			.when()
			.delete(TRAVEL_ENTRY_POINT + "/" + travelId)
			.then().log().all()
			.extract();

		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	@DisplayName("단건 여행 조회")
	@Test
	void find_travel_by_id() {
		final Long cityId = 도시_등록();
		final Long travelId = 여행_등록(cityId);

		final String token = 로그인();

		final ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header("Authorization", "Bearer " + token)
			.when()
			.get(TRAVEL_ENTRY_POINT + "/" + travelId)
			.then().log().all()
			.extract();

		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	public static Long 여행_등록(final Long cityId) {
		final String token = 로그인();

		final String travelName = "서울 여행";
		LocalDate startDate = LocalDate.of(22, 12, 1);
		LocalDate endDate = LocalDate.of(22, 12, 10);

		final RegisterTravelRequest request = new RegisterTravelRequest(travelName, cityId, startDate, endDate);

		return RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header("Authorization", "Bearer " + token)
			.body(request)
			.when()
			.post(TRAVEL_ENTRY_POINT + "/register")
			.then().log().all()
			.extract()
			.jsonPath().getLong("result.id");
	}

	public static void 여행_시작(final Long cityId) {
		final String token = 로그인();

		final Long travelId = 여행_등록(cityId);

		final String travelName = "서울 여행";
		final TravelState travelState = TravelState.TRAVELING;
		LocalDate startDate = LocalDate.of(22, 12, 1);
		LocalDate endDate = LocalDate.of(22, 12, 10);

		final UpdateTravelRequest request = new UpdateTravelRequest(travelName, travelState, startDate, endDate);

		RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header("Authorization", "Bearer " + token)
			.body(request)
			.when()
			.patch(TRAVEL_ENTRY_POINT + "/" + travelId)
			.then().log().all()
			.extract();
	}

}
