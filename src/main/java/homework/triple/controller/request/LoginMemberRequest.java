package homework.triple.controller.request;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginMemberRequest {

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

}
