package homework.triple.controller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import homework.triple.acceptance.AcceptanceTest;
import homework.triple.controller.fixtures.MemberFixtures;
import homework.triple.controller.request.JoinMemberRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

class MemberControllerTest extends AcceptanceTest {

	private static final String MEMBER_ENTRY_POINT = "/member";

	@DisplayName("회원가입 테스트")
	@Test
	void join() {
		final JoinMemberRequest joinMemberRequest = new JoinMemberRequest("hiro", "1234");

		final ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(joinMemberRequest)
			.when()
			.post(MEMBER_ENTRY_POINT + "/join")
			.then().log().all()
			.extract();

		assertAll(
			() -> assertThat(response.jsonPath().getString("resultCode")).isEqualTo("success"),
			() -> assertThat(response.jsonPath().getString("result")).isNotEmpty()
		);
	}

	@DisplayName("로그인 테스트")
	@Test
	void login() {
		회원가입();

		final ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(MemberFixtures.loginMemberRequest())
			.when()
			.post(MEMBER_ENTRY_POINT + "/login")
			.then().log().all()
			.extract();

		assertAll(
			() -> assertThat(response.jsonPath().getString("resultCode")).isEqualTo("success"),
			() -> assertThat(response.jsonPath().getString("result.token")).isNotEmpty()
		);
	}

	public static void 회원가입() {
		RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(MemberFixtures.joinMemberRequest())
			.when()
			.post("/member/join")
			.then().log().all()
			.extract();
	}


}
