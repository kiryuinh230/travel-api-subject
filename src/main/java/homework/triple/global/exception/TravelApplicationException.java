package homework.triple.global.exception;

import homework.triple.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class TravelApplicationException extends RuntimeException {

	private ErrorCode errorCode;
	private String message;

	public TravelApplicationException(final ErrorCode errorCode) {
		this.errorCode = errorCode;
		this.message = errorCode.getMessage();
	}

	public TravelApplicationException(final ErrorCode errorCode, final String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	@Override
	public String getMessage() {
		if (message == null) {
			return errorCode.getMessage();
		}
		return String.format("%s. %s", errorCode.getMessage(), message);
	}

}
