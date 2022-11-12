package homework.triple.controller;

import homework.triple.controller.request.JoinMemberRequest;
import homework.triple.controller.request.LoginMemberRequest;
import homework.triple.global.CommonResponse;
import homework.triple.controller.response.LoginMemberResponse;
import homework.triple.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/member/join")
	public CommonResponse<Void> join(@RequestBody @Valid final JoinMemberRequest request) {
		final Long userId = memberService.join(request.getUsername(), request.getPassword());

		return CommonResponse.success(userId);
	}

	@PostMapping("/member/login")
	public CommonResponse<String> login(@RequestBody @Valid final LoginMemberRequest request) {
		final String token = memberService.login(request.getUsername(), request.getPassword());

		return CommonResponse.success(new LoginMemberResponse(token));
	}

}
