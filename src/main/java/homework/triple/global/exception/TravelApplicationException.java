package homework.triple.global.exception;

import homework.triple.global.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TravelApplicationException extends RuntimeException {

	private ErrorCode errorCode;
	private String message;

	@Override
	public String getMessage() {
		if (message == null) {
			return errorCode.getMessage();
		}
		return String.format("%s. %s", errorCode.getMessage(), message);
	}

}
