package homework.triple.controller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import homework.triple.acceptance.AcceptanceTest;
import homework.triple.controller.fixtures.MemberFixtures;
import homework.triple.controller.request.JoinMemberRequest;
import homework.triple.global.error.ErrorCode;
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

	@DisplayName("중복된 아이디로 회원가입시 실패한다.")
	@Test
	void duplication_username() {
		회원가입();

		final ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(MemberFixtures.loginMemberRequest())
			.when()
			.post(MEMBER_ENTRY_POINT + "/join")
			.then().log().all()
			.extract();

		assertAll(
			() -> assertThat(response.jsonPath().getString("resultCode")).isEqualTo(ErrorCode.DUPLICATED_USERNAME.name()),
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

	@DisplayName("유효하지 않은 아이디로 로그인시 실패한다")
	@Test
	void not_found_member() {
		final ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(MemberFixtures.loginMemberRequest())
			.when()
			.post(MEMBER_ENTRY_POINT + "/login")
			.then().log().all()
			.extract();

		assertThat(response.jsonPath().getString("resultCode")).isEqualTo(ErrorCode.USER_NOT_FOUND.name());
	}

	public static void 회원가입() {
		RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(MemberFixtures.joinMemberRequest())
			.when()
			.post(MEMBER_ENTRY_POINT + "/join")
			.then().log().all()
			.extract();
	}

	public static String 로그인() {
		회원가입();

		final String token = RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(MemberFixtures.loginMemberRequest())
			.when()
			.post(MEMBER_ENTRY_POINT + "/login")
			.then().log().all()
			.extract()
			.jsonPath().getString("result");

		return token;
	}

}
