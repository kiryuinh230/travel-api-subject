package homework.triple.domain.exception;

import homework.triple.global.error.ErrorCode;
import javax.persistence.EntityNotFoundException;
import lombok.Getter;

@Getter
public class MemberNotFoundException extends EntityNotFoundException {

	private final ErrorCode errorCode;
	private final String message;

	public MemberNotFoundException(final ErrorCode errorCode, final String message) {
		this.errorCode = errorCode;
		this.message = message;
	}
}
