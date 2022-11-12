package homework.triple.controller;

import static homework.triple.controller.MemberControllerTest.로그인;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import homework.triple.acceptance.AcceptanceTest;
import homework.triple.controller.request.RegisterCityRequest;
import homework.triple.controller.request.UpdateCityRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
			.header("Authorization", "Bearer " + token)
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
