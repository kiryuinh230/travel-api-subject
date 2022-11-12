package homework.triple.domain.exception;

import homework.triple.global.error.ErrorCode;
import homework.triple.global.exception.TravelApplicationException;

public class CannotDeleteCityException extends TravelApplicationException {

	public CannotDeleteCityException(final ErrorCode errorCode) {
		super(errorCode);
	}
}
