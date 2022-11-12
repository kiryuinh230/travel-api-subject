package homework.triple.domain.exception;

import homework.triple.global.error.ErrorCode;
import javax.persistence.EntityNotFoundException;
import lombok.Getter;

@Getter
public class CityNotFoundException extends EntityNotFoundException {

	private final ErrorCode errorCode;
	private final String message;

	public CityNotFoundException(final ErrorCode errorCode) {
		this.errorCode = errorCode;
		this.message = errorCode.getMessage();
	}
}
