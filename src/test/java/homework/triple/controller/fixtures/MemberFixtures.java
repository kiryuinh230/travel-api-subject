package homework.triple.controller.fixtures;

import homework.triple.controller.request.JoinMemberRequest;
import homework.triple.controller.request.LoginMemberRequest;

public class MemberFixtures {

	private static final String USERNAME = "hiro";
	private static final String PASSWORD = "1234";

	public static JoinMemberRequest joinMemberRequest() {
		return new JoinMemberRequest(USERNAME, PASSWORD);
	}

	public static LoginMemberRequest loginMemberRequest() {
		return new LoginMemberRequest(USERNAME, PASSWORD);
	}
}
