package homework.triple.controller;

import static homework.triple.controller.MemberControllerTest.로그인;
import static homework.triple.controller.TravelControllerTest.여행_등록;
import static homework.triple.controller.TravelControllerTest.여행_시작;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import homework.triple.acceptance.AcceptanceTest;
import homework.triple.controller.request.RegisterCityRequest;
import homework.triple.controller.request.UpdateCityRequest;
import homework.triple.global.error.ErrorCode;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

class CityControllerTest extends AcceptanceTest {

	private static final String CITY_ENTRY_POINT = "/city";

	@DisplayName("도시 등록 테스트")
	@Test
	void register_city() {
		final String token = 로그인();

		final String cityName = "seoul";

		RegisterCityRequest request = new RegisterCityRequest(cityName);

		final ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
			.body(request)
			.when()
			.post(CITY_ENTRY_POINT + "/register")
			.then().log().all()
			.extract();

		assertAll(
			() -> assertThat(response.jsonPath().getString("resultCode")).isEqualTo("success"),
			() -> assertThat(response.jsonPath().getString("result.id")).isNotEmpty(),
			() -> assertThat(response.jsonPath().getString("result.cityName")).isEqualTo(cityName)
		);
	}

	@DisplayName("도시 이름 수정 테스트")
	@Test
	void update_city() {
		final Long cityId = 도시_등록();

		final String token = 로그인();

		final String updateCityName = "pangyo";

		UpdateCityRequest request = new UpdateCityRequest(updateCityName);

		final ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header("Authorization", "Bearer " + token)
			.body(request)
			.when()
			.patch(CITY_ENTRY_POINT + "/" + cityId)
			.then().log().all()
			.extract();

		assertAll(
			() -> assertThat(response.jsonPath().getString("resultCode")).isEqualTo("success"),
			() -> assertThat(response.jsonPath().getString("result.id")).isEqualTo(cityId.toString()),
			() -> assertThat(response.jsonPath().getString("result.updateCityName")).isEqualTo(updateCityName)
		);

	}

	@DisplayName("단일 도시 조회 ")
	@Test
	void find_city() {
		final Long cityId = 도시_등록();

		final String token = 로그인();

		final ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header("Authorization", "Bearer " + token)
			.when()
			.get(CITY_ENTRY_POINT + "/" + cityId)
			.then().log().all()
			.extract();

		assertAll(
			() -> assertThat(response.jsonPath().getString("resultCode")).isEqualTo("success"),
			() -> assertThat(response.jsonPath().getString("result.id")).isEqualTo(cityId.toString()),
			() -> assertThat(response.jsonPath().getString("result.cityName")).isEqualTo("seoul")
		);
	}

	@DisplayName("도시 삭제 테스트")
	@Test
	void delete_city() {
		final Long cityId = 도시_등록();

		final String token = 로그인();

		final ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header("Authorization", "Bearer " + token)
			.when()
			.delete(CITY_ENTRY_POINT + "/" + cityId)
			.then().log().all()
			.extract();

		assertThat(response.jsonPath().getString("resultCode"))
			.isEqualTo("success");
	}

	@DisplayName("도시 삭제 예외 테스트 - 도시가 지정된 여행이 존재하는 경우 ")
	@Test
	void delete_city_exception() {
		final Long cityId = 도시_등록();

		final Long travelId = 여행_등록(cityId);
		여행_시작(travelId);

		final String token = 로그인();

		final ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header("Authorization", "Bearer " + token)
			.when()
			.delete(CITY_ENTRY_POINT + "/" + cityId)
			.then().log().all()
			.extract();

			assertThat(response.jsonPath().getString("resultCode"))
				.isEqualTo(ErrorCode.CANNOT_DELETE_CITY.name());
	}

	public static Long 도시_등록() {
		final String token = 로그인();

		final String cityName = "seoul";

		RegisterCityRequest request = new RegisterCityRequest(cityName);

		return RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header("Authorization", "Bearer " + token)
			.body(request)
			.when()
			.post(CITY_ENTRY_POINT + "/register")
			.then().log().all()
			.extract()
			.jsonPath()
			.getLong("result.id");
	}
}
