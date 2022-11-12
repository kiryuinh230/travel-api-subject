package homework.triple.domain.exception;

import homework.triple.global.error.ErrorCode;
import homework.triple.global.exception.TravelApplicationException;

public class TravelDateException extends TravelApplicationException {

	public TravelDateException() {
		super(ErrorCode.TRAVEL_DATE_INVALID);
	}
}
