package homework.triple.domain.exception;

import homework.triple.global.error.ErrorCode;
import homework.triple.global.exception.TravelApplicationException;

public class CannotUpdateTravelException extends TravelApplicationException {

	private static final String MESSAGE = "여행 등록자만이 여행을 수정할 수 있습니다.";
	public CannotUpdateTravelException(final ErrorCode errorCode) {
		super(errorCode, MESSAGE);
	}
}
