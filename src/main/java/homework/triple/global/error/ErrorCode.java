package homework.triple.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error"),
	USER_NOT_FOUND(HttpStatus.CONFLICT, "not found user"),
	INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "token is invalid"),
	DUPLICATED_USERNAME(HttpStatus.CONFLICT, "duplicated username"),
	WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "wrong password"),
	DUPLICATED_CITY_NAME(HttpStatus.CONFLICT, "duplicated city name"),
	CITY_NOT_FOUND(HttpStatus.CONFLICT, "not found city"),
	TRAVEL_NOT_FOUNT(HttpStatus.CONFLICT, "not found travel"),
	INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "permission is invalid"),
	TRAVEL_DATE_INVALID(HttpStatus.BAD_REQUEST, "travel start date is after end date");

	private final HttpStatus httpStatus;
	private final String message;

}
