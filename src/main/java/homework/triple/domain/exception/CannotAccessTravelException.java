package homework.triple.domain.exception;

import homework.triple.global.error.ErrorCode;
import homework.triple.global.exception.TravelApplicationException;

public class CannotAccessTravelException extends TravelApplicationException {

	private static final String MESSAGE = "본인이 등록한 여행만 접근할 수 있습니다.";

	public CannotAccessTravelException(final ErrorCode errorCode) {
		super(errorCode, MESSAGE);
	}
}
