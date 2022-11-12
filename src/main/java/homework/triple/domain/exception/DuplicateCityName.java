package homework.triple.domain.exception;

import homework.triple.global.error.ErrorCode;
import homework.triple.global.exception.TravelApplicationException;

public class DuplicateCityName extends TravelApplicationException {

	public DuplicateCityName(final ErrorCode errorCode) {
		super(errorCode);
	}
}
