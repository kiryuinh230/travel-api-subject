package homework.triple.domain.exception;

import homework.triple.global.error.ErrorCode;
import javax.persistence.EntityNotFoundException;

public class TravelNotFoundEntityException extends EntityNotFoundException {

	private final ErrorCode errorCode;
	private final String message;

	public TravelNotFoundEntityException(final ErrorCode errorCode) {
		this.errorCode = errorCode;
		this.message = errorCode.getMessage();
	}
}
